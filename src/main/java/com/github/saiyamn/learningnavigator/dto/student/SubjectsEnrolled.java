package com.github.saiyamn.learningnavigator.dto.student;

public class SubjectsEnrolled {

    private long subjectId;
    private String subjectName;


    public SubjectsEnrolled(){

    }
    public SubjectsEnrolled(long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
