package com.github.saiyamn.learningnavigator.controller;

import com.github.saiyamn.learningnavigator.dto.student.AddStudentDTO;
import com.github.saiyamn.learningnavigator.dto.student.StudentRequestBody;
import com.github.saiyamn.learningnavigator.dto.student.StudentResponseBody;
import com.github.saiyamn.learningnavigator.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    IStudentService iStudentService;

    @PostMapping
    public ResponseEntity<StudentResponseBody> addStudent(@RequestBody StudentRequestBody studentRequestBody) {
        return new ResponseEntity<StudentResponseBody>(iStudentService.addStudent(studentRequestBody.getUserName()), HttpStatus.OK);
    }

    @GetMapping(value="/{studentId}")
    public ResponseEntity<StudentResponseBody> getStudent(@PathVariable String studentId) {
        return new ResponseEntity<StudentResponseBody>(iStudentService.getStudent(studentId),HttpStatus.OK);
    }

}
