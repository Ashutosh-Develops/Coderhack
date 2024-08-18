package com.github.saiyamn.learningnavigator.service.subject;

import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollmentResponseBody;
import com.github.saiyamn.learningnavigator.dto.subject.SubjectResponseBody;
import com.github.saiyamn.learningnavigator.entity.Enrollment;
import com.github.saiyamn.learningnavigator.entity.Student;
import com.github.saiyamn.learningnavigator.entity.Subject;
import com.github.saiyamn.learningnavigator.exception.InvalidInputException;
import com.github.saiyamn.learningnavigator.exception.StudentEnrolledException;
import com.github.saiyamn.learningnavigator.exception.StudentNotFoundException;
import com.github.saiyamn.learningnavigator.exception.SubjectNotFoundException;
import com.github.saiyamn.learningnavigator.repository.IEnrollmentRepository;
import com.github.saiyamn.learningnavigator.repository.IStudentRepository;
import com.github.saiyamn.learningnavigator.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ISubjectService{

    @Autowired
    ISubjectRepository iSubjectRepository;
    @Autowired
    IEnrollmentRepository iEnrollmentRepository;
    @Autowired
    IStudentRepository iStudentRepository;

    public SubjectService(ISubjectRepository iSubjectRepository, IEnrollmentRepository iEnrollmentRepository, IStudentRepository iStudentRepository) {
        this.iSubjectRepository = iSubjectRepository;
        this.iEnrollmentRepository = iEnrollmentRepository;
        this.iStudentRepository = iStudentRepository;
    }

    @Override
    public SubjectResponseBody addSubject(String subjectName) {

        if(subjectName==null || subjectName.isEmpty()){
            throw new InvalidInputException("Invalid subject name"+subjectName);
        }

        Subject subject=new Subject(subjectName);
        Subject savedSubject=iSubjectRepository.save(subject);
        SubjectResponseBody subjectResponseBody=new SubjectResponseBody(savedSubject);

        return subjectResponseBody;
    }

    @Override
    public EnrollmentResponseBody enrollStudentToCourse(String studentId, String courseId) {

        if(studentId==null||studentId.isEmpty()){
            throw new InvalidInputException("Invalid student with studentId"+studentId);

        }
        if(courseId==null||courseId.isEmpty()){
            throw new InvalidInputException("Invalid course with courseId"+studentId);
        }

        Long studentIdLong=Long.parseLong(studentId);
        Long courseIdLong=Long.parseLong(courseId);

        // Check if the student exists
        if(!iStudentRepository.existsById(studentIdLong)){
            throw new StudentNotFoundException("Student with student id "+studentId+"does not exist");
        }

        // Check if the course exists
        if(!iSubjectRepository.existsById(courseIdLong)){
            throw new SubjectNotFoundException("Subject with subject id "+courseId+" does not exist");
        }

        // if Both exist then check whether they are enrolled already . If yes then throw Exception
        List<Enrollment> enrollments= (List<Enrollment>) iEnrollmentRepository.findAll();

        for(Enrollment enrollment:enrollments){
            if(enrollment.getStudent().getId()==studentIdLong && enrollment.getSubject().getId()==courseIdLong){
                throw new StudentEnrolledException("Student with student id "+studentId+" is already enrolled in the subject with subject id "+courseId);
            }
        }

        Optional<Student> studentOptional = iStudentRepository.findById(studentIdLong);
        Optional<Subject> subjectOptional=iSubjectRepository.findById(courseIdLong);

        Enrollment enrollment=new Enrollment(studentOptional.get(),subjectOptional.get());
        Enrollment savedEnrollment=iEnrollmentRepository.save(enrollment);
        EnrollmentResponseBody enrollmentResponseBody=new EnrollmentResponseBody(savedEnrollment);

        return enrollmentResponseBody;
    }
}
