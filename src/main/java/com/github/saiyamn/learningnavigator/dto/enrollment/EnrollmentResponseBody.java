package com.github.saiyamn.learningnavigator.dto.enrollment;

import com.github.saiyamn.learningnavigator.entity.Enrollment;

public class EnrollmentResponseBody {


    private long enrollmentId;
    private EnrollmentStudentInfo enrollmentStudentInfo;
    private EnrollmentSubjectInfo enrollmentSubjectInfo;

    public EnrollmentResponseBody(){

    }

    public EnrollmentResponseBody(Enrollment enrollment){
        this.enrollmentId=enrollment.getId();
        enrollmentStudentInfo=new EnrollmentStudentInfo(enrollment.getStudent().getId(),enrollment.getStudent().getUserName());
        enrollmentSubjectInfo=new EnrollmentSubjectInfo(enrollment.getSubject().getId(),enrollment.getSubject().getSubjectName());
    }

    public long getEnrollmentId() {
        return enrollmentId;
    }

    public EnrollmentStudentInfo getEnrollmentStudentInfo() {
        return enrollmentStudentInfo;
    }

    public EnrollmentSubjectInfo getEnrollmentSubjectInfo() {
        return enrollmentSubjectInfo;
    }

    public void setEnrollmentId(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public void setEnrollmentStudentInfo(EnrollmentStudentInfo enrollmentStudentInfo) {
        this.enrollmentStudentInfo = enrollmentStudentInfo;
    }

    public void setEnrollmentSubjectInfo(EnrollmentSubjectInfo enrollmentSubjectInfo) {
        this.enrollmentSubjectInfo = enrollmentSubjectInfo;
    }
}
