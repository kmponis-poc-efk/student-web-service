package com.kbonis.student.web.service.services.implementations;

import com.kbonis.student.web.service.models.Student;
import com.kbonis.student.web.service.repository.StudentRepository;
import com.kbonis.student.web.service.services.ManageDatabase;
import com.kbonis.student.web.service.services.StudentService;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ManageDatabase manageDatabase;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(String studentId) {
        studentRepository.delete(studentId);
    }

    @Override
    public void removeCourseId(String courseId) {
        List<Student> students = studentRepository.findByCourseIdsContaining(courseId);
        for (Student student: students) {
            String[] courseIds = student.getCourseIds();
            courseIds = ArrayUtils.removeElement(courseIds, courseId);
            student.setCourseIds(courseIds);
            studentRepository.save(student);
        }
    }

    @Override
    public Student findByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> studentsByLastName = studentRepository.findByLastName(name);
        List<Student> studentsByFirstName = studentRepository.findByFirstName(name);
        return ListUtils.union(studentsByLastName, studentsByFirstName);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void initialiseStudents() {
        manageDatabase.deleteStudents();
        manageDatabase.addStudents();
    }
    
}
