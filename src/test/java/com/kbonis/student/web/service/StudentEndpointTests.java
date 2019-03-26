package com.kbonis.student.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kbonis.student.web.service.repository.StudentRepository;
import com.kbonis.student.web.service.services.ManageDatabase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbonis.student.web.service.models.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentEndpointTests {
    
    final String BASE_PATH = "http://localhost:8888/student";
    final String SESSION_ID = "?sessionId=qwesdad";

	@Autowired
	private StudentRepository studentRepository;
    
    @Autowired
    private ManageDatabase manageDatabase;

    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        manageDatabase.deleteStudents();
        manageDatabase.addStudents();
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testCreateStudent() throws JsonProcessingException {
        Student student = new Student("Leandro", "Costarica", "12331", "leandro.costa@asd.asd", new String[]{"12345234"});
        Student response = restTemplate.postForObject(BASE_PATH + SESSION_ID, student, Student.class);
        assertEquals("Leandro Costarica", response.getFirstName() + " " + response.getLastName());
    }
    
    @Test
    public void testUpdateStudent() throws IOException { 
        List<Student> students = studentRepository.findAll();

        Student student = studentRepository.findByStudentId(students.get(2).getStudentId());
        student.setFirstName("Tiago");
        student.setLastName("Ferreira");
        restTemplate.put(BASE_PATH + SESSION_ID, student);

        Student student2 = studentRepository.findByStudentId(students.get(2).getStudentId());
        assertNotNull(student2);
    	assertEquals("Tiago", student2.getFirstName());
    	assertEquals("Ferreira", student2.getLastName());
    }

    // TODO: Remove @ignore
    @Ignore
    @Test
    public void testDeleteStudent() throws IOException {
        List<Student> students1 = studentRepository.findAll();
        Student student1 = students1.get(0);
        
        // TODO: fix delete call, throws 404
        restTemplate.delete(BASE_PATH + "/delete/"+ student1.getStudentId() + SESSION_ID);

        List<Student> students2 = studentRepository.findAll();
        Student student2 = students2.get(0);
        assertNotEquals(student1.getStudentId(), student2.getStudentId());
    }

    @Test
    public void testRemoveCourseId() throws IOException {
        Student student1 = studentRepository.save(new Student("Student1", "Student1", "1", "student1@asd.asd", new String[]{"a123456789", "b123456789", "c123456789"}));
        restTemplate.delete(BASE_PATH + "/removeCourseId/a123456789" + SESSION_ID);
        student1 = studentRepository.findByStudentId(student1.getStudentId());
        assertEquals(2, student1.getCourseIds().length);
        studentRepository.delete(student1);
    }

    @Test
    public void testFindByStudentId() throws IOException {
        List<Student> students = studentRepository.findAll();
        Student student = restTemplate.getForObject(BASE_PATH + "/findByStudentId/" + students.get(1).getStudentId() + SESSION_ID, Student.class);
        assertNotNull(student);
    	assertEquals("Eudes", student.getFirstName());
    	assertEquals("Silva", student.getLastName());
    }

    @Test
    public void testFindByName() throws IOException {
        List<Student> students = studentRepository.findAll();
        List<Student> studentsByLastName = restTemplate.getForObject(BASE_PATH + "/findByName/" + students.get(1).getLastName() + SESSION_ID, ArrayList.class);
        assertNotNull(studentsByLastName);
        assertThat(studentsByLastName).hasSize(2).extracting("firstName").contains("Eudes", "Anderson");
        assertThat(studentsByLastName).hasSize(2).extracting("lastName").contains("Silva", "Silva");
        assertThat(studentsByLastName).hasSize(2).extracting("phone").contains("0299999999", "0399999999");
        assertThat(studentsByLastName).hasSize(2).extracting("email").contains("eudes.silva@asd.asd", "anderson.silva@asd.asd");
    }

    @Test
    public void testFindAll() throws IOException {
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll" + SESSION_ID, String.class);
        List<Student> students = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class));
    	assertEquals("Diego", students.get(0).getFirstName());
    	assertEquals("Samuel", students.get(0).getLastName());
    }

    @Test
    public void testInitialiseStudents() {
        studentRepository.save(new Student("Leandro", "Costarica", "12331", "leandro.costa@asd.asd", new String[]{"12345234"}));
        List<Student> studentsBefore = studentRepository.findAll();
        assertEquals(6, studentsBefore.size());
        
        restTemplate.getForObject(BASE_PATH + "/initialiseStudents" + SESSION_ID, String.class);

        List<Student> studentsAfter = studentRepository.findAll();
        assertEquals(5, studentsAfter.size());
        assertThat(studentsAfter).hasSize(5).extracting("studentId").contains("5c1257f4501032f77101306a", "5c1257f4501032f77101306b", "5c1257f4501032f77101306c", "5c1257f4501032f77101306d", "5c1257f4501032f77101306e");
        assertThat(studentsAfter).hasSize(5).extracting("firstName").contains("Diego", "Eudes", "Anderson", "Alice", "Alan");
        assertThat(studentsAfter).hasSize(5).extracting("lastName").contains("Samuel", "Silva", "Silva", "Ferreira", "Franco");
        assertThat(studentsAfter).hasSize(5).extracting("phone").contains("0199999999", "0299999999", "0399999999", "0499999999", "0599999999");
        assertThat(studentsAfter).hasSize(5).extracting("email").contains("diego.samuel@asd.asd", "eudes.silva@asd.asd", "anderson.silva@asd.asd", "alice.ferreira@asd.asd", "alan.franco@asd.asd");
    }
}