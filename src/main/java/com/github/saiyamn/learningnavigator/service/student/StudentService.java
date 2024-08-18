package com.github.saiyamn.learningnavigator.service.student;

import com.github.saiyamn.learningnavigator.dto.student.StudentResponseBody;
import com.github.saiyamn.learningnavigator.entity.Student;
import com.github.saiyamn.learningnavigator.exception.InvalidInputException;
import com.github.saiyamn.learningnavigator.exception.StudentNotFoundException;
import com.github.saiyamn.learningnavigator.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{

    @Autowired
    IStudentRepository iStudentRepository;

    public StudentService(IStudentRepository iStudentRepository) {
        this.iStudentRepository = iStudentRepository;
    }

    @Override
    public StudentResponseBody addStudent(String userName) {

        if(userName==null || userName.isEmpty()){
            throw new InvalidInputException("Invalid username "+userName);
        }
        Student student=new Student(userName);
        Student savedStudent=iStudentRepository.save(student);
        StudentResponseBody studentResponseBody=new StudentResponseBody(savedStudent);

        return studentResponseBody;
    }

    @Override
    public StudentResponseBody getStudent(String studentId) {

        if(studentId==null||studentId.isEmpty()){
            throw new InvalidInputException("Invalid student id "+studentId);
        }

        long studentIdLong=Long.parseLong(studentId);
        Optional<Student> studentOptional=iStudentRepository.findById(studentIdLong);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Student with student id "+studentId+" is not present");
        }

        StudentResponseBody studentResponseBody=new StudentResponseBody(studentOptional.get());

        return studentResponseBody;
    }
}
