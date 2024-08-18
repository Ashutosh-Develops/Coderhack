package com.github.saiyamn.learningnavigator.dto.subject;

import com.github.saiyamn.learningnavigator.entity.Enrollment;
import com.github.saiyamn.learningnavigator.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectResponseBody {

    private long subjectId;
    private String subjectName;
    private List<SubjectStudentInfo> subjectStudentInfos;

    public SubjectResponseBody(){

    }
    public SubjectResponseBody(Subject subject){
        this.subjectId=subject.getId();
        this.subjectName=subject.getSubjectName();
        this.subjectStudentInfos=getStudentInfoFromSubjectEnrollment(subject.getStudentsEnrolled());
    }

    private List<SubjectStudentInfo> getStudentInfoFromSubjectEnrollment(List<Enrollment> enrollments){

        List<SubjectStudentInfo> subjectStudentInfoList=new ArrayList<>();

        for(Enrollment enrollment:enrollments){
            subjectStudentInfoList.add(new SubjectStudentInfo(enrollment.getStudent().getId(),enrollment.getStudent().getUserName()));
        }

        return subjectStudentInfoList;
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

    public List<SubjectStudentInfo> getSubjectStudentInfos() {
        return subjectStudentInfos;
    }

    public void setSubjectStudentInfos(List<SubjectStudentInfo> subjectStudentInfos) {
        this.subjectStudentInfos = subjectStudentInfos;
    }
}
