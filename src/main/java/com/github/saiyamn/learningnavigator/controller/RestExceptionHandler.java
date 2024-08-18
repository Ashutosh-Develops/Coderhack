package com.github.saiyamn.learningnavigator.controller;

import com.github.saiyamn.learningnavigator.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value= InvalidInputException.class)
    public ResponseEntity handleInvalidInputException(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= ExamNotFoundException.class)
    public ResponseEntity<String> handleExamNotFoundException(){
        return new ResponseEntity<>("Exam not found",HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value= StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(){
        return new ResponseEntity<>("Student not found",HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value= SubjectNotFoundException.class)
    public ResponseEntity<String> handleSubjectNotFoundException(){
        return new ResponseEntity<>("Subject not found",HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value= StudentEnrolledException.class)
    public ResponseEntity<String> handleStudentEnrolledException(StudentEnrolledException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value= ExamScheduledException.class)
    public ResponseEntity<String> handleExamScheduledException(ExamScheduledException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
