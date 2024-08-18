package com.github.saiyamn.learningnavigator.repository;

import com.github.saiyamn.learningnavigator.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends CrudRepository<Student,Long> {
}
