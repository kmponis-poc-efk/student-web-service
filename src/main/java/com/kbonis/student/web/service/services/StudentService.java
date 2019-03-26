package com.kbonis.student.web.service.services;

import com.kbonis.student.web.service.models.Student;

import java.util.List;

public interface StudentService {

    /**
     * Create Student
     * @param student Student
     * @return Student
     */
    Student create(Student student);

    /**
     * Update Student
     * @param student Student
     * @return Student
     */
    Student update(Student student);

    /**
     * Delete Student
     * @param id String
     */
    void delete(String id);

    /**
     * Remove courseId from Students
     * @param courseId String
     */
    void removeCourseId(String courseId);

    /**
     * Get Student by student id
     * @param studentId String
     * @return Student
     */
    Student findByStudentId(String studentId);

    /**
     * Get Student by name
     * @param firstName or lastName String
     * @return Student
     */
    List<Student> findByName(String name);

    /**
     * Get all Students
     * @return List<Student>
     */
    List<Student> findAll();

    /**
     * Initialise students in Database
     */
    void initialiseStudents();
}
