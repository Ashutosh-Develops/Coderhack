package com.github.saiyamn.learningnavigator.dto.examRegistration;

import com.github.saiyamn.learningnavigator.entity.Exam;
import com.github.saiyamn.learningnavigator.entity.ExamRegistration;
import com.github.saiyamn.learningnavigator.entity.Student;

public class ExamRegistrationResponseBody {

    private long examRegistrationId;

    private StudentRegistered studentRegistered;

    private ExamInfo examInfo;

    public ExamRegistrationResponseBody() {
    }

    public ExamRegistrationResponseBody(ExamRegistration examRegistration){

        this.examRegistrationId=examRegistration.getId();
        this.studentRegistered=getStudentRegisteredForTheExam(examRegistration.getStudent());
        this.examInfo=getExamInfoFromExam(examRegistration.getExam());
    }

    private StudentRegistered getStudentRegisteredForTheExam(Student student){

        return new StudentRegistered(student.getId(),student.getUserName());
    }

    private ExamInfo getExamInfoFromExam(Exam exam){
        return new ExamInfo(exam.getId(),exam.getSubject().getId(),exam.getSubject().getSubjectName());
    }

    public long getExamRegistrationId() {
        return examRegistrationId;
    }

    public void setExamRegistrationId(long examRegistrationId) {
        this.examRegistrationId = examRegistrationId;
    }

    public StudentRegistered getStudentRegistered() {
        return studentRegistered;
    }

    public void setStudentRegistered(StudentRegistered studentRegistered) {
        this.studentRegistered = studentRegistered;
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }
}
