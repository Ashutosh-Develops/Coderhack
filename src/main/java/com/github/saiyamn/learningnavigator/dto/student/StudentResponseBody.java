package com.github.saiyamn.learningnavigator.dto.student;

import com.github.saiyamn.learningnavigator.entity.Enrollment;
import com.github.saiyamn.learningnavigator.entity.ExamRegistration;
import com.github.saiyamn.learningnavigator.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentResponseBody {

    private long studentId;
    private String studentName;

    private List<SubjectsEnrolled> subjectsEnrolled;
    private List<ExamsRegistered> examsRegistered;

    public StudentResponseBody(){

    }

    public StudentResponseBody(Student student){

        this.studentId=student.getId();
        this.studentName=student.getUserName();
        this.subjectsEnrolled=getSubjectsEnrolledByStudent(student.getEnrollments());
        this.examsRegistered=getExamsRegisteredByStudent(student.getExamsRegistered());
    }

    private List<SubjectsEnrolled> getSubjectsEnrolledByStudent(List<Enrollment> enrollments){

        List<SubjectsEnrolled> subjectsEnrolledList=new ArrayList<>();

        for(Enrollment enrollment:enrollments){
            subjectsEnrolledList.add(new SubjectsEnrolled(enrollment.getSubject().getId(),enrollment.getSubject().getSubjectName()));
        }
        return subjectsEnrolledList;
    }

    private List<ExamsRegistered> getExamsRegisteredByStudent(List<ExamRegistration> examRegistrations){

        List<ExamsRegistered> examsRegisteredList=new ArrayList<>();

        for(ExamRegistration examRegistration:examRegistrations){
            examsRegisteredList.add(new ExamsRegistered(examRegistration.getExam().getId(),examRegistration.getExam().getSubject().getSubjectName()));
        }

        return examsRegisteredList;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<SubjectsEnrolled> getSubjectsEnrolled() {
        return subjectsEnrolled;
    }

    public void setSubjectsEnrolled(List<SubjectsEnrolled> subjectsEnrolled) {
        this.subjectsEnrolled = subjectsEnrolled;
    }

    public List<ExamsRegistered> getExamsRegistered() {
        return examsRegistered;
    }

    public void setExamsRegistered(List<ExamsRegistered> examsRegistered) {
        this.examsRegistered = examsRegistered;
    }
}
