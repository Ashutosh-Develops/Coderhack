package com.github.saiyamn.learningnavigator.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {


    /*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<ExamRegistration> examsRegistered;

    public Student(){

    }

    public Student(long id, String userName, List<Enrollment> enrollments, List<ExamRegistration> examsRegistered) {
        this.id = id;
        this.userName = userName;
        this.enrollments = enrollments;
        this.examsRegistered = examsRegistered;
    }

    public Student(String userName) {
        this.userName = userName;
        this.enrollments = new ArrayList<>();
        this.examsRegistered = new ArrayList<>();
    }

    


    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      result = prime * result + ((userName == null) ? 0 : userName.hashCode());
      result = prime * result + ((enrollments == null) ? 0 : enrollments.hashCode());
      result = prime * result + ((examsRegistered == null) ? 0 : examsRegistered.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Student other = (Student) obj;
      if (id != other.id)
        return false;
      if (userName == null) {
        if (other.userName != null)
          return false;
      } else if (!userName.equals(other.userName))
        return false;
      if (enrollments == null) {
        if (other.enrollments != null)
          return false;
      } else if (!enrollments.equals(other.enrollments))
        return false;
      if (examsRegistered == null) {
        if (other.examsRegistered != null)
          return false;
      } else if (!examsRegistered.equals(other.examsRegistered))
        return false;
      return true;
    }

    public String getUserName() {
        return userName;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public List<ExamRegistration> getExamsRegistered() {
        return examsRegistered;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void setExamsRegistered(List<ExamRegistration> examsRegistered) {
        this.examsRegistered = examsRegistered;
    }
}
