package com.github.saiyamn.learningnavigator.entity;

import jakarta.persistence.*;

@Entity
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public ExamRegistration(){

    }

    public ExamRegistration(long id, Student student, Exam exam) {
        this.id = id;
        this.student = student;
        this.exam = exam;
    }

    public ExamRegistration(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
    }

    

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      result = prime * result + ((student == null) ? 0 : student.hashCode());
      result = prime * result + ((exam == null) ? 0 : exam.hashCode());
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
      ExamRegistration other = (ExamRegistration) obj;
      if (id != other.id)
        return false;
      if (student == null) {
        if (other.student != null)
          return false;
      } else if (!student.equals(other.student))
        return false;
      if (exam == null) {
        if (other.exam != null)
          return false;
      } else if (!exam.equals(other.exam))
        return false;
      return true;
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

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
