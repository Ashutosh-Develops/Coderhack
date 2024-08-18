package com.github.saiyamn.learningnavigator.repository;

import com.github.saiyamn.learningnavigator.entity.ExamRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRegistrationRepository extends CrudRepository<ExamRegistration,Long> {
}
