package com.kbonis.student.web.service.web.controllers;

import com.kbonis.student.web.service.models.Student;
import com.kbonis.student.web.service.services.StudentService;
import com.sopra.steria.LogTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO Make this endpoints accecible by swagger.
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student create(@RequestBody Student student, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().create(sessionId=" + sessionId + ")");
		Student created = studentService.create(student);
		LogTimes.endTiming("StudentController().create(sessionId=" + sessionId + ")", "info");
		return created;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student update(@RequestBody Student student, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().update(sessionId=" + sessionId + ")");
		Student updated = studentService.update(student);
		LogTimes.endTiming("StudentController().update(sessionId=" + sessionId + ")", "info");
		return updated;
	}

	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "studentId") String studentId, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().delete(sessionId=" + sessionId + ")");
		studentService.delete(studentId);
		LogTimes.endTiming("StudentController().delete(sessionId=" + sessionId + ")", "info");
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/removeCourseId/{courseId}", method = RequestMethod.DELETE)
	public void removeCourseId(@PathVariable(value = "courseId") String courseId, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().removeCourseId(sessionId=" + sessionId + ")");
		studentService.removeCourseId(courseId);
		LogTimes.endTiming("StudentController().removeCourseId(sessionId=" + sessionId + ")", "info");
	}

	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/findByStudentId/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findByStudentId(@PathVariable(value = "studentId") String studentId, @RequestParam("sessionId") String sessionId) {
		LogTimes.startTiming("StudentController().findByStudentId(sessionId=" + sessionId + ")");
		Student findByStudentId = studentService.findByStudentId(studentId);
		LogTimes.endTiming("StudentController().findByStudentId(sessionId=" + sessionId + ")", "info");
		return findByStudentId;
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findByName(@PathVariable(value = "name") String name, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().findByName(sessionId=" + sessionId + ")");
		List<Student> findByLastName = studentService.findByName(name);
		LogTimes.endTiming("StudentController().findByName(sessionId=" + sessionId + ")", "info");
		return findByLastName;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findAll(@RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().findAll(sessionId=" + sessionId + ")");
		List<Student> findAll = studentService.findAll();
		LogTimes.endTiming("StudentController().findAll(sessionId=" + sessionId + ")", "info");
		return findAll;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/initialiseStudents", method = RequestMethod.GET)
	public void initialiseStudents(@RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("StudentController().initialiseStudents(sessionId=" + sessionId + ")");
		studentService.initialiseStudents();
		LogTimes.endTiming("StudentController().initialiseStudents(sessionId=" + sessionId + ")", "info");
	}
}
