package com.github.saiyamn.learningnavigator.controller;

import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollToCourseDTO;
import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollmentRequestBody;
import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollmentResponseBody;
import com.github.saiyamn.learningnavigator.dto.subject.AddSubjectDTO;
import com.github.saiyamn.learningnavigator.dto.subject.SubjectRequestBody;
import com.github.saiyamn.learningnavigator.dto.subject.SubjectResponseBody;
import com.github.saiyamn.learningnavigator.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
public class CourseController {

    @Autowired
    private ISubjectService iSubjectService;

    @PostMapping
    public ResponseEntity<SubjectResponseBody> addSubject(@RequestBody SubjectRequestBody subjectRequestBody){
        SubjectResponseBody responseBody=iSubjectService.addSubject(subjectRequestBody.getSubjectName());
        return new ResponseEntity<SubjectResponseBody>(responseBody, HttpStatus.OK);
    }

    @PutMapping(value = "/{courseId}")
    public ResponseEntity<EnrollmentResponseBody> enrollStudentToCourse(@RequestBody EnrollmentRequestBody enrollmentRequestBody, @PathVariable String courseId){
        EnrollmentResponseBody responseBody= iSubjectService.enrollStudentToCourse(enrollmentRequestBody.getStudentId(),courseId);
        return new ResponseEntity<EnrollmentResponseBody>(responseBody,HttpStatus.OK);
    }
}
