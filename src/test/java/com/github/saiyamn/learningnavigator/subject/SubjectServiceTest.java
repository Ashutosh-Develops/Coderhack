package com.github.saiyamn.learningnavigator.subject;

import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollmentResponseBody;
import com.github.saiyamn.learningnavigator.entity.Enrollment;
import com.github.saiyamn.learningnavigator.entity.Student;
import com.github.saiyamn.learningnavigator.entity.Subject;
import com.github.saiyamn.learningnavigator.exception.StudentEnrolledException;
import com.github.saiyamn.learningnavigator.repository.IEnrollmentRepository;
import com.github.saiyamn.learningnavigator.repository.IStudentRepository;
import com.github.saiyamn.learningnavigator.repository.ISubjectRepository;
import com.github.saiyamn.learningnavigator.service.subject.ISubjectService;
import com.github.saiyamn.learningnavigator.service.subject.SubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SubjectServiceTest {

    // Test Enrollment of Student to the course

    @Test
    @DisplayName("Enroll student to subject")
    public void registerStudentToSubject(){

        ISubjectRepository iSubjectRepository= Mockito.mock(ISubjectRepository.class);
        IStudentRepository iStudentRepository=Mockito.mock(IStudentRepository.class);
        IEnrollmentRepository iEnrollmentRepository=Mockito.mock(IEnrollmentRepository.class);

        ISubjectService iSubjectService=new SubjectService(iSubjectRepository,iEnrollmentRepository,iStudentRepository);

        Student student = getAStudentInstance();
        Subject subject = getASubjectInstance();

        Optional<Student> studentOptional=Optional.of(student);
        Optional<Subject> subjectOptional=Optional.of(subject);

        long studentIdLong = student.getId();
        long courseIdLong = subject.getId();
        Enrollment enrollment=new Enrollment(student,subject);
        Enrollment savedEnrollment=new Enrollment(1l,student,subject);

        Mockito.when(iStudentRepository.existsById(studentIdLong)).thenReturn(true);
        Mockito.when(iSubjectRepository.existsById(courseIdLong)).thenReturn(true);
        Mockito.when(iEnrollmentRepository.findAll()).thenReturn(new ArrayList<Enrollment>());
        Mockito.when(iStudentRepository.findById(studentIdLong)).thenReturn(studentOptional);
        Mockito.when(iSubjectRepository.findById(courseIdLong)).thenReturn(subjectOptional);
        Mockito.when(iEnrollmentRepository.save(Mockito.eq(enrollment))).thenReturn(savedEnrollment);

        EnrollmentResponseBody enrollmentResponseBody=iSubjectService.enrollStudentToCourse(String.valueOf(studentIdLong),String.valueOf(courseIdLong));

        Assertions.assertEquals(1l,enrollmentResponseBody.getEnrollmentId());
        Assertions.assertEquals(1l,enrollmentResponseBody.getEnrollmentStudentInfo().getStudentId());
        Assertions.assertEquals(1l,enrollmentResponseBody.getEnrollmentSubjectInfo().getSubjectId());
    }


    @Test
    @DisplayName("Register a student that is already enrolled")
    public void registerAnAlreadyRegisteredStudentTest(){


        ISubjectRepository iSubjectRepository= Mockito.mock(ISubjectRepository.class);
        IStudentRepository iStudentRepository=Mockito.mock(IStudentRepository.class);
        IEnrollmentRepository iEnrollmentRepository=Mockito.mock(IEnrollmentRepository.class);

        ISubjectService iSubjectService=new SubjectService(iSubjectRepository,iEnrollmentRepository,iStudentRepository);

        Student student = getAStudentInstance();
        Subject subject = getASubjectInstance();

        Optional<Student> studentOptional=Optional.of(student);
        Optional<Subject> subjectOptional=Optional.of(subject);

        long studentIdLong = student.getId();
        long courseIdLong = subject.getId();
        Enrollment enrollment=new Enrollment(student,subject);
        Enrollment savedEnrollment=new Enrollment(1l,student,subject);
        List<Enrollment> enrollmentList= Arrays.asList(savedEnrollment);

        Mockito.when(iStudentRepository.existsById(studentIdLong)).thenReturn(true);
        Mockito.when(iSubjectRepository.existsById(courseIdLong)).thenReturn(true);
        Mockito.when(iEnrollmentRepository.findAll()).thenReturn(enrollmentList);


       Exception studentEnrolledException= Assertions.assertThrows(StudentEnrolledException.class,()->{
           iSubjectService.enrollStudentToCourse(String.valueOf(studentIdLong),String.valueOf(courseIdLong));
       });


       String expectedMessage= "Student with student id "+studentIdLong+" is already enrolled in the subject with subject id "+courseIdLong;
       String actualMessage= studentEnrolledException.getMessage();

       Assertions.assertEquals(expectedMessage,actualMessage);
    }

    private Student getAStudentInstance(){
        Student student=new Student("Ajax");
        student.setId(1l);

        return student;
    }

    private Subject getASubjectInstance(){
        Subject subject=new Subject("Computer Science");
        subject.setId(1l);

        return subject;
    }

}
