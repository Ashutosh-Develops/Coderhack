package com.github.saiyamn.learningnavigator.dto.subject;

public class AddSubjectDTO {

    private String subjectName;

    public AddSubjectDTO(){

    }

    public AddSubjectDTO(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
