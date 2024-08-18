package com.github.saiyamn.learningnavigator.dto.enrollment;

public class EnrollmentStudentInfo {

    private long studentId;
    private String studentName;

    public EnrollmentStudentInfo(){

    }

    public EnrollmentStudentInfo(long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
