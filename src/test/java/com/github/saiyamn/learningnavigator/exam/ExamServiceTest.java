package com.github.saiyamn.learningnavigator.exam;

import com.github.saiyamn.learningnavigator.dto.examRegistration.ExamRegistrationResponseBody;
import com.github.saiyamn.learningnavigator.entity.*;
import com.github.saiyamn.learningnavigator.exception.StudentEnrolledException;
import com.github.saiyamn.learningnavigator.repository.*;
import com.github.saiyamn.learningnavigator.service.exam.ExamService;
import com.github.saiyamn.learningnavigator.service.exam.IExamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExamServiceTest {



    @Test
    @DisplayName("Register a user to the exam enrolled in the course")
    public void registerUserToAnExam(){


        IExamRepository iExamRepository= Mockito.mock(IExamRepository.class);
        IExamRegistrationRepository iExamRegistrationRepository=Mockito.mock(IExamRegistrationRepository.class);
        ISubjectRepository iSubjectRepository = Mockito.mock(ISubjectRepository.class);
        IStudentRepository iStudentRepository = Mockito.mock(IStudentRepository.class);
        IEnrollmentRepository iEnrollmentRepository = Mockito.mock(IEnrollmentRepository.class);

        IExamService iExamService = new ExamService(iExamRepository,iExamRegistrationRepository,iSubjectRepository,iStudentRepository,iEnrollmentRepository);

        Optional<Student> studentOptional=getStudentInstance();
        Optional<Exam> examOptional=getExamInstance();

        long studentIdLong = studentOptional.get().getId();
        long examIdLong = examOptional.get().getId();

        List<Enrollment> enrollments= Arrays.asList(getEnrollment());

        Mockito.when(iStudentRepository.findById(studentIdLong)).thenReturn(studentOptional);
        Mockito.when(iExamRepository.findById(examIdLong)).thenReturn(examOptional);
        Mockito.when(iEnrollmentRepository.findAll()).thenReturn(enrollments);

        ExamRegistration examRegistration=new ExamRegistration(studentOptional.get(),examOptional.get());
        ExamRegistration savedExamRegistration=new ExamRegistration(1l,studentOptional.get(),examOptional.get());
        Mockito.when(iExamRegistrationRepository.save(Mockito.eq(examRegistration))).thenReturn(savedExamRegistration);


        ExamRegistrationResponseBody returnedExamRegistrationResponseBody=iExamService.registerUserToExam(String.valueOf(studentIdLong),String.valueOf(examIdLong));

        Assertions.assertEquals(1l,returnedExamRegistrationResponseBody.getExamRegistrationId());
        Assertions.assertEquals(1l,returnedExamRegistrationResponseBody.getStudentRegistered().getStudentId());
        Assertions.assertEquals(1l,returnedExamRegistrationResponseBody.getExamInfo().getExamId());
        Assertions.assertEquals("Ajax",returnedExamRegistrationResponseBody.getStudentRegistered().getStudentName());
        Assertions.assertEquals("Computer Science",returnedExamRegistrationResponseBody.getExamInfo().getSubjectName());


    }

    @Test
    @DisplayName("Register student not enrolled in a course to the exam")
    public void registerStudentNotEnrolledInTheCourseToTheExam(){

        IExamRepository iExamRepository= Mockito.mock(IExamRepository.class);
        IExamRegistrationRepository iExamRegistrationRepository=Mockito.mock(IExamRegistrationRepository.class);
        ISubjectRepository iSubjectRepository = Mockito.mock(ISubjectRepository.class);
        IStudentRepository iStudentRepository = Mockito.mock(IStudentRepository.class);
        IEnrollmentRepository iEnrollmentRepository = Mockito.mock(IEnrollmentRepository.class);

        IExamService iExamService = new ExamService(iExamRepository,iExamRegistrationRepository,iSubjectRepository,iStudentRepository,iEnrollmentRepository);

        Optional<Student> studentOptional=getStudentInstance();
        Optional<Exam> examOptional=getExamInstance();

        long studentIdLong = studentOptional.get().getId();
        long examIdLong = examOptional.get().getId();

        List<Enrollment> enrollments= new ArrayList<>();

        Mockito.when(iStudentRepository.findById(studentIdLong)).thenReturn(studentOptional);
        Mockito.when(iExamRepository.findById(examIdLong)).thenReturn(examOptional);
        Mockito.when(iEnrollmentRepository.findAll()).thenReturn(enrollments);

        Exception exception = Assertions.assertThrows(StudentEnrolledException.class,()->{
            iExamService.registerUserToExam(String.valueOf(studentIdLong),String.valueOf(examIdLong));
        });

        String actualMessage=exception.getMessage();
        String expectedMessage="Student with student id "+studentIdLong+" is not enrolled in course "+examOptional.get().getSubject().getSubjectName()+" with subject id "+examOptional.get().getSubject().getId();


        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    private Optional<Student> getStudentInstance(){

        Student student = new Student("Ajax");
        student.setId(1l);

        return Optional.of(student);
    }

    private Optional<Exam> getExamInstance(){

        Exam exam=new Exam(getSubjectInstance());
        exam.setId(1l);

        return Optional.of(exam);
    }

    private Subject getSubjectInstance(){
        Subject subject=new Subject("Computer Science");
        subject.setId(1l);

        return subject;
    }

    private Enrollment getEnrollment(){

        Enrollment enrollment=new Enrollment(getStudentInstance().get(),getSubjectInstance());
        enrollment.setId(1l);

        return enrollment;
    }
}
