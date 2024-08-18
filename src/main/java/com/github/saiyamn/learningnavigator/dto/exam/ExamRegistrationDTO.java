package com.github.saiyamn.learningnavigator.dto.exam;

public class ExamRegistrationDTO {

    private String studentId;

    public ExamRegistrationDTO(){

    }

    public ExamRegistrationDTO(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
