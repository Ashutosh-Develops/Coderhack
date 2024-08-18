package com.github.saiyamn.learningnavigator.dto.exam;

public class ExamRequestBody {

    private String courseId;

    public ExamRequestBody() {
    }

    public ExamRequestBody(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
