package com.github.saiyamn.learningnavigator.entity;

import jakarta.persistence.*;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    public Enrollment(){

    }

    

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      result = prime * result + ((student == null) ? 0 : student.hashCode());
      result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
      Enrollment other = (Enrollment) obj;
      if (id != other.id)
        return false;
      if (student == null) {
        if (other.student != null)
          return false;
      } else if (!student.equals(other.student))
        return false;
      if (subject == null) {
        if (other.subject != null)
          return false;
      } else if (!subject.equals(other.subject))
        return false;
      return true;
    }



    public Enrollment(long id, Student student, Subject subject) {
        this.id = id;
        this.student = student;
        this.subject = subject;
    }

    public Enrollment(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    
}
