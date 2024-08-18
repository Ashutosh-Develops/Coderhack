package com.github.saiyamn.learningnavigator.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY)
    private List<ExamRegistration> studentsRegistered;

   public Exam(){

   }

    public Exam(long id, Subject subject, List<ExamRegistration> studentsRegistered) {
        this.id = id;
        this.subject = subject;
        this.studentsRegistered = studentsRegistered;
    }

    public Exam(Subject subject){
       this.subject=subject;
       this.studentsRegistered=new ArrayList<>();
    }

    

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      result = prime * result + ((subject == null) ? 0 : subject.hashCode());
      result = prime * result + ((studentsRegistered == null) ? 0 : studentsRegistered.hashCode());
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
      Exam other = (Exam) obj;
      if (id != other.id)
        return false;
      if (subject == null) {
        if (other.subject != null)
          return false;
      } else if (!subject.equals(other.subject))
        return false;
      if (studentsRegistered == null) {
        if (other.studentsRegistered != null)
          return false;
      } else if (!studentsRegistered.equals(other.studentsRegistered))
        return false;
      return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<ExamRegistration> getStudentsRegistered() {
        return studentsRegistered;
    }

    public void setStudentsRegistered(List<ExamRegistration> studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }
}
