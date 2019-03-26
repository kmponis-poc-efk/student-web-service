package com.kbonis.student.web.service.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String studentId;

	private String firstName;

	private String lastName;

	private String phone;

	private String email;

	private String[] courseIds;

	public Student() {}

	public Student(String studentId, String firstName,String lastName, String phone, String email, String[] courseIds) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.courseIds = courseIds;
	}

	public Student(String firstName,String lastName, String phone, String email, String[] courseIds) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.courseIds = courseIds;
	}

}