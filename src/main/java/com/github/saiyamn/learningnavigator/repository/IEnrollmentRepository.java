package com.github.saiyamn.learningnavigator.repository;

import com.github.saiyamn.learningnavigator.entity.Enrollment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnrollmentRepository extends CrudRepository<Enrollment,Long> {
}
