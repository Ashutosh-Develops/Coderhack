package com.github.saiyamn.learningnavigator.dto.subject;

public class SubjectRequestBody {

    private String subjectName;

    public SubjectRequestBody() {
    }

    public SubjectRequestBody(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
