package com.github.saiyamn.learningnavigator.repository;

import com.github.saiyamn.learningnavigator.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepository extends CrudRepository<Subject,Long> {
}
