package com.github.saiyamn.learningnavigator.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String subjectName;

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
    private List<Enrollment> studentsEnrolled;

    public Subject(){

    }

    public Subject(long id, String subjectName, List<Enrollment> studentsEnrolled) {
        this.id=id;
        this.subjectName = subjectName;
        this.studentsEnrolled = studentsEnrolled;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
        this.studentsEnrolled = new ArrayList<>();
    }

    


    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
      result = prime * result + ((studentsEnrolled == null) ? 0 : studentsEnrolled.hashCode());
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
      Subject other = (Subject) obj;
      if (id != other.id)
        return false;
      if (subjectName == null) {
        if (other.subjectName != null)
          return false;
      } else if (!subjectName.equals(other.subjectName))
        return false;
      if (studentsEnrolled == null) {
        if (other.studentsEnrolled != null)
          return false;
      } else if (!studentsEnrolled.equals(other.studentsEnrolled))
        return false;
      return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<Enrollment> getStudentsEnrolled() {
        return studentsEnrolled;
    }



    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setStudentsEnrolled(List<Enrollment> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }


}
