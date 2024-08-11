package com.github.machinecodingchallenge.coderhack.controller;

import java.util.List;

import com.github.machinecodingchallenge.coderhack.dto.rest_input_format.ScoreUpdate;
import com.github.machinecodingchallenge.coderhack.entity.Contest;
import com.github.machinecodingchallenge.coderhack.exception.ContestProcessingCouldNotProceedException;
import com.github.machinecodingchallenge.coderhack.exception.ContestResourcesNotFoundException;
import com.github.machinecodingchallenge.coderhack.exception.InvalidInputException;
import com.github.machinecodingchallenge.coderhack.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.machinecodingchallenge.coderhack.dto.RegisteredUser;
import com.github.machinecodingchallenge.coderhack.dto.rest_input_format.UserInput;

@RestController
@RequestMapping("/api/v1/users")
public class ContestController {

  @Autowired
  private ContestService contestService;


  @GetMapping(value = "/contests")
  public List<Contest> getContests(){

    return contestService.getContests();
  }

  // Register a user to the contest
  // POST request /user
  @PostMapping
  public ResponseEntity<RegisteredUser> registerUser(@RequestBody UserInput userInput){

    RegisteredUser registeredUser= null;
    try {
      registeredUser = this.contestService.registerUser(userInput);

    } catch (InvalidInputException  | ContestProcessingCouldNotProceedException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }catch(Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<RegisteredUser>(registeredUser,HttpStatus.CREATED);

  }


  // Get all the registered users
  @GetMapping
  public ResponseEntity<List<RegisteredUser>> getRegisteredUsers(){

    List<RegisteredUser> registeredUsers= null;
    try {
      registeredUsers = contestService.getRegisteredUsers();

    } catch (ContestResourcesNotFoundException e) {
       new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }catch(Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<List<RegisteredUser>>(registeredUsers,HttpStatus.OK);
  }

  // Get a specific registered user
  @GetMapping(value="/{userId}")
  public ResponseEntity<RegisteredUser> getRegisteredUser(@PathVariable String userId){

    RegisteredUser registeredUser= null;
    try {
      registeredUser = contestService.getRegisteredUser(userId);

    } catch (ContestResourcesNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } catch (InvalidInputException e) {
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }catch(Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<RegisteredUser>(registeredUser,HttpStatus.OK);

  }

  // update score of registered user
  @PutMapping
  public ResponseEntity<RegisteredUser> setRegisteredUserScore(@RequestBody ScoreUpdate scoreUpdate){

    RegisteredUser registeredUser = null;
    try {
      registeredUser = contestService.setRegisteredUserScore(scoreUpdate.getUserId(), scoreUpdate.getScore());

    } catch (InvalidInputException | ContestProcessingCouldNotProceedException e) {
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e){
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<RegisteredUser>(registeredUser,HttpStatus.OK);
  }

  // get leaderboard
  @DeleteMapping(value="/{userId}")
  public ResponseEntity deRegisterUserFromContest(@PathVariable String userId){

    try {
      contestService.deRegisterUser(userId);
    } catch (InvalidInputException | ContestProcessingCouldNotProceedException e) {
       return new ResponseEntity(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(value = "/leaderboard")
  public ResponseEntity<List<RegisteredUser>> getContestLeaderBoard(){

    List<RegisteredUser> registeredUsers=null;

    try {
       registeredUsers=contestService.getContestLeaderBoard();
    } catch (ContestResourcesNotFoundException e) {
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }catch(Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<List<RegisteredUser>>(registeredUsers,HttpStatus.OK);
  }

  
  
}
