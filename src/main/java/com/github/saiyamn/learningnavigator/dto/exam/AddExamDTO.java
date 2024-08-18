package com.github.saiyamn.learningnavigator.dto.exam;

public class AddExamDTO {

    private String courseId;

    public AddExamDTO(){

    }

    public AddExamDTO(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
