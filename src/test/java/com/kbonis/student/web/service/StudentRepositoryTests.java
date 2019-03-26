package com.kbonis.student.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbonis.student.web.service.models.Student;
import com.kbonis.student.web.service.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentRepositoryTests {

    @Autowired
    StudentRepository studentRepository;

    @Before
    public void setUp() { }

    @Test
    public void testFindByStudentId() {
        Student diego = new Student("Diego", "Samuel", "234", "diego.samuel@asd.asd", new String[]{"12345123"});
        studentRepository.save(diego);
        Student repositoryResponse = studentRepository.findByStudentId(diego.getStudentId());
        assertEquals(diego.getStudentId(), repositoryResponse.getStudentId());
        studentRepository.delete(diego);
    }

    @Test
    public void testFindByFirstName() {
        Student leandrose = studentRepository.save(new Student("Leandrose", "Costas", "243", "leandros.costas@asd.asd", new String[]{"1234325"}));
        List<Student> result = studentRepository.findByFirstName("Leandrose");
        assertThat(result).hasSize(1).extracting("lastName").contains("Costas");
        studentRepository.delete(leandrose);
    }

    @Test
    public void testFindByLastName() {
        Student costarino = studentRepository.save(new Student("Leandros", "Costarino", "243", "leandros.costas@asd.asd", new String[]{"1234df5"}));
        List<Student> result = studentRepository.findByLastName("Costarino");
        assertThat(result).hasSize(1).extracting("firstName").contains("Leandros");
        studentRepository.delete(costarino);
    }

    @Test
    public void testFindByCourseIds() {
        Student leandro = studentRepository.save(new Student("Leandros", "Costas", "243", "leandros.costas@asd.asd", new String[]{"1234325", "12345678"}));
        Student sandra = studentRepository.save(new Student("Sandra", "Loola", "243144", "sandra.loola@asd.asd", new String[]{"1234325", "7563728"}));
        List<Student> result = studentRepository.findByCourseIdsContaining("1234325");
        assertThat(result).hasSize(2).extracting("lastName").contains("Costas", "Loola");
        studentRepository.delete(leandro);
        studentRepository.delete(sandra);
    }
}
