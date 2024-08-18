package com.github.saiyamn.learningnavigator.dto.enrollment;

public class EnrollmentSubjectInfo {

    private long subjectId;
    private String studentName;

    public EnrollmentSubjectInfo(){

    }

    public EnrollmentSubjectInfo(long subjectId, String studentName) {
        this.subjectId = subjectId;
        this.studentName = studentName;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
