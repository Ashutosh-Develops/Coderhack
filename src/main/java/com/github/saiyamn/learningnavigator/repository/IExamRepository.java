package com.github.saiyamn.learningnavigator.repository;

import com.github.saiyamn.learningnavigator.entity.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRepository extends CrudRepository<Exam,Long> {
}
