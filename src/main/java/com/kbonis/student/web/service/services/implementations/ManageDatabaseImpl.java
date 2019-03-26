package com.kbonis.student.web.service.services.implementations;

import java.util.List;

import com.kbonis.student.web.service.repository.StudentRepository;
import com.kbonis.student.web.service.services.ManageDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.kbonis.student.web.service.models.Student;

@Service
public class ManageDatabaseImpl implements ManageDatabase {
    
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addStudents() {
        studentRepository.save(new Student("5c1257f4501032f77101306a", "Diego", "Samuel", "0199999999", "diego.samuel@asd.asd", new String[]{"5c0fac73ba7ba50d161e671a", "5c0fac73ba7ba50d161e671c"}));
        studentRepository.save(new Student("5c1257f4501032f77101306b", "Eudes", "Silva", "0299999999", "eudes.silva@asd.asd", new String[]{"5c0fac73ba7ba50d161e671b"}));
        studentRepository.save(new Student("5c1257f4501032f77101306c", "Anderson", "Silva", "0399999999", "anderson.silva@asd.asd", new String[]{"5c0fac73ba7ba50d161e671d", "5c0fac73ba7ba50d161e671c"}));
		studentRepository.save(new Student("5c1257f4501032f77101306d", "Alice", "Ferreira", "0499999999", "alice.ferreira@asd.asd", new String[]{"5c0fac73ba7ba50d161e671c"}));
		studentRepository.save(new Student("5c1257f4501032f77101306e", "Alan", "Franco", "0599999999", "alan.franco@asd.asd", new String[]{"5c0fac73ba7ba50d161e671a", "5c0fac73ba7ba50d161e671b"}));
    }

    @Override
    public void deleteStudents() {
        studentRepository.delete(findAllStudents());
    }

    private List<Student> findAllStudents() {
        return studentRepository.findAll(Example.of(new Student()));
    }
}