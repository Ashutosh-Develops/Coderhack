package com.github.saiyamn.learningnavigator.controller;

import com.github.saiyamn.learningnavigator.dto.exam.*;
import com.github.saiyamn.learningnavigator.dto.examRegistration.ExamRegistrationResponseBody;
import com.github.saiyamn.learningnavigator.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    @Autowired
    private IExamService iExamService;

    @PostMapping
    public ResponseEntity<ExamResponseBody> addExam(@RequestBody ExamRequestBody examRequestBody){
        return new ResponseEntity<ExamResponseBody>(iExamService.addExam(examRequestBody.getCourseId()), HttpStatus.OK);
    }

    @PostMapping(value = "/{examId}")
    public ResponseEntity<ExamRegistrationResponseBody> registerUserToExam(@RequestBody ExamRegistrationRequestBody examRegistrationRequestBody, @PathVariable String examId){
           return new ResponseEntity<ExamRegistrationResponseBody>(iExamService.registerUserToExam(examRegistrationRequestBody.getStudentId(),examId),HttpStatus.OK);
    }
}

