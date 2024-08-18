package com.github.saiyamn.learningnavigator.dto.exam;

public class ExamRegistrationRequestBody {

    private String studentId;

    public ExamRegistrationRequestBody() {
    }

    public ExamRegistrationRequestBody(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
