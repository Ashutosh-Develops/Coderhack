package com.github.saiyamn.learningnavigator.dto.enrollment;

public class EnrollToCourseDTO {

    private String studentId;

    public EnrollToCourseDTO(){

    }

    public EnrollToCourseDTO(String studentId) {

        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
