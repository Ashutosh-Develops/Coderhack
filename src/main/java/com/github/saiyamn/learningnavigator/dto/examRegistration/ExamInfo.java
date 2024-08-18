package com.github.saiyamn.learningnavigator.dto.examRegistration;

public class ExamInfo {

    private long examId;

    private long subjectId;

    private String subjectName;

    public ExamInfo() {
    }

    public ExamInfo(long examId, long subjectId, String subjectName) {

        this.examId = examId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
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
