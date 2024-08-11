package com.github.machinecodingchallenge.coderhack.repository;

import java.util.Optional;



import com.github.machinecodingchallenge.coderhack.entity.Contest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContestRepository extends MongoRepository<Contest,String> {


}
