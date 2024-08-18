package com.github.saiyamn.learningnavigator.dto.enrollment;

public class EnrollmentRequestBody {

    private String studentId;

    public EnrollmentRequestBody() {
    }

    public EnrollmentRequestBody(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
