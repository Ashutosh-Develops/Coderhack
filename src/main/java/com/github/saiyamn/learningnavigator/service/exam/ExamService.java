package com.github.saiyamn.learningnavigator.service.exam;

import com.github.saiyamn.learningnavigator.dto.exam.ExamResponseBody;
import com.github.saiyamn.learningnavigator.dto.examRegistration.ExamRegistrationResponseBody;
import com.github.saiyamn.learningnavigator.entity.*;
import com.github.saiyamn.learningnavigator.exception.*;
import com.github.saiyamn.learningnavigator.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService implements  IExamService{

    @Autowired
    private IExamRepository iExamRepository;
    @Autowired
    private IExamRegistrationRepository iExamRegistrationRepository;
    @Autowired
    private ISubjectRepository iSubjectRepository;
    @Autowired
    private IStudentRepository iStudentRepository;
    @Autowired
    private IEnrollmentRepository iEnrollmentRepository;


    public ExamService(IExamRepository iExamRepository, IExamRegistrationRepository iExamRegistrationRepository, ISubjectRepository iSubjectRepository, IStudentRepository iStudentRepository, IEnrollmentRepository iEnrollmentRepository) {
        this.iExamRepository = iExamRepository;
        this.iExamRegistrationRepository = iExamRegistrationRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iStudentRepository = iStudentRepository;
        this.iEnrollmentRepository = iEnrollmentRepository;
    }

    @Override
    public ExamResponseBody addExam(String courseId) {

        if(courseId==null || courseId.isEmpty()){
            throw new InvalidInputException("Invalid course with course id "+courseId);
        }

        Long courseIdLong=Long.parseLong(courseId);
        if(!iSubjectRepository.existsById(courseIdLong)){
            throw new SubjectNotFoundException("Course with course id "+courseId+" does not exist");
        }

        List<Exam> exams= (List<Exam>) iExamRepository.findAll();
        for(Exam exam:exams){
            if(exam.getSubject().getId()==courseIdLong){
                throw new ExamScheduledException("Exam has already been scheduled for subject with subject id "+courseId);
            }
        }

        Subject subject=iSubjectRepository.findById(courseIdLong).get();
        Exam exam = new Exam(subject);
        Exam savedExam=iExamRepository.save(exam);
        ExamResponseBody examResponseBody=new ExamResponseBody(savedExam);

        return examResponseBody;
    }

    @Override
    public ExamRegistrationResponseBody registerUserToExam(String studentId, String examId) {

        if(studentId==null || studentId.isEmpty()){
            throw new InvalidInputException("Invalid student id "+studentId);
        }

        if(examId==null||examId.isEmpty()){
            throw new InvalidInputException("Invalid exam id "+examId);
        }

        long studentIdLong= Long.parseLong(studentId);
        long examIdLong = Long.parseLong(examId);
        Optional<Student> studentOptional=iStudentRepository.findById(studentIdLong);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Student with student id "+studentId+" does not exist");
        }

        Student student=studentOptional.get();
        Optional<Exam> examOptional=iExamRepository.findById(examIdLong);
        if(examOptional.isEmpty()){
            throw new ExamNotFoundException("Exam with exam id "+examId+" does not exist");
        }

        Exam exam=examOptional.get();
        List<Enrollment> enrollments= (List<Enrollment>) iEnrollmentRepository.findAll();
        boolean isEnrolled=false;
        for(Enrollment enrollment:enrollments){
            if(enrollment.getStudent().getId()==studentIdLong&&enrollment.getSubject().getId()==exam.getSubject().getId()){
                isEnrolled=true;
                break;
            }
        }

        // When student is not registered in course
        if(isEnrolled==false){
            throw new StudentEnrolledException("Student with student id "+studentId+" is not enrolled in course "+exam.getSubject().getSubjectName()+" with subject id "+exam.getSubject().getId());
        }

        ExamRegistration examRegistration=new ExamRegistration(student,exam);
        ExamRegistration savedExamRegistration=iExamRegistrationRepository.save(examRegistration);
        ExamRegistrationResponseBody examRegistrationResponseBody=new ExamRegistrationResponseBody(savedExamRegistration);

        return examRegistrationResponseBody;
    }
}
