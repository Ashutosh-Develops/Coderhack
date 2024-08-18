package com.github.saiyamn.learningnavigator.dto.examRegistration;

public class StudentRegistered {

    private long studentId;
    private String studentName;

    public StudentRegistered() {
    }

    public StudentRegistered(long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
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
}
