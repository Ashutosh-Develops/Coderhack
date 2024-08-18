package com.github.saiyamn.learningnavigator.dto.student;

public class ExamsRegistered {

    private long examId;
    private String subjectName;

    public ExamsRegistered(){

    }

    public ExamsRegistered(long examId, String subjectName) {
        this.examId = examId;
        this.subjectName = subjectName;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
