package com.github.saiyamn.learningnavigator.dto.exam;

import com.github.saiyamn.learningnavigator.entity.Exam;
import com.github.saiyamn.learningnavigator.entity.ExamRegistration;

import java.util.ArrayList;
import java.util.List;

public class ExamResponseBody {

    private long examId;

    private Subject subject;

    private List<RegisteredStudents> registeredStudents;


    public ExamResponseBody() {
    }

    public ExamResponseBody(Exam exam){
        this.examId=exam.getId();
        this.subject=getSubjectDetailFromExam(exam.getSubject());
        this.registeredStudents=getRegisteredStudentsFromExam(exam.getStudentsRegistered());
    }

    private Subject getSubjectDetailFromExam(com.github.saiyamn.learningnavigator.entity.Subject subject ){

        return new Subject(subject.getId(),subject.getSubjectName());
    }

    private List<RegisteredStudents> getRegisteredStudentsFromExam(List<ExamRegistration> examRegistrations){

        List<RegisteredStudents> registeredStudentsList=new ArrayList<>();

        for(ExamRegistration examRegistration:examRegistrations){
            registeredStudentsList.add(new RegisteredStudents(examRegistration.getStudent().getId(),examRegistration.getStudent().getUserName()));
        }
        return registeredStudentsList;

    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<RegisteredStudents> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(List<RegisteredStudents> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
}
