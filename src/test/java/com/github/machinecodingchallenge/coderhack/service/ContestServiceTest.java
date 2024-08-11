package com.github.machinecodingchallenge.coderhack.service;

import com.github.machinecodingchallenge.coderhack.dto.RegisteredUser;
import com.github.machinecodingchallenge.coderhack.dto.rest_input_format.UserInput;
import com.github.machinecodingchallenge.coderhack.entity.Contest;
import com.github.machinecodingchallenge.coderhack.exception.ContestProcessingCouldNotProceedException;
import com.github.machinecodingchallenge.coderhack.exception.ContestResourcesNotFoundException;
import com.github.machinecodingchallenge.coderhack.exception.InvalidInputException;
import com.github.machinecodingchallenge.coderhack.repository.IContestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import java.util.*;

public class ContestServiceTest {

    private IContestRepository iContestRepository;
    public void setup(){
        // Repository instance
        // contest sService instance

    }

    // Throw Invalid Input Exception
    @Test
    @DisplayName("Register user with an invalid user id")
    public void registerUserWithInvalidUserid(){
        iContestRepository=null;
        UserInput userInput=new UserInput("ajax","");
        ContestService contestService=new ContestService(iContestRepository);
        Exception exceptionEmptyUserId=Assertions.assertThrows(InvalidInputException.class,()->{
            contestService.registerUser(userInput);
        });

        String actualMessageEmptyUserId="Invalid user id "+userInput.getUserId();
        String expectedMessageEmptyUserId = exceptionEmptyUserId.getMessage();

        Assertions.assertEquals(expectedMessageEmptyUserId,actualMessageEmptyUserId);


        UserInput userInput2=new UserInput("ajax",null);
        Exception exceptionNullUserId=Assertions.assertThrows(InvalidInputException.class,()->{
            contestService.registerUser(userInput2);
        });

        String actualMessageNullUserId="Invalid user id "+userInput2.getUserId();
        String expectedMessageNullUserId = exceptionNullUserId.getMessage();

        Assertions.assertEquals(expectedMessageNullUserId,actualMessageNullUserId);

    }

    @Test
    @DisplayName("Register user with an invalid Username")
    public void registerUserWithInvalidUsername(){

        iContestRepository=null;
        UserInput userInput=new UserInput("","1");
        ContestService contestService=new ContestService(iContestRepository);
        Exception exceptionEmptyUserName=Assertions.assertThrows(InvalidInputException.class,()->{
            contestService.registerUser(userInput);
        });

        String actualMessageeEmptyUserName="Invalid username "+userInput.getUsername();
        String expectedMessageEmptyUserName=exceptionEmptyUserName.getMessage();

        Assertions.assertEquals(expectedMessageEmptyUserName,actualMessageeEmptyUserName);



        UserInput userInput2=new UserInput(null,"1");
        Exception exceptionNullUserName=Assertions.assertThrows(InvalidInputException.class,()->{
            contestService.registerUser(userInput2);
        });

        String actualMessageNullUserName="Invalid username "+userInput2.getUsername();
        String expectedMessageNullUserName=exceptionNullUserName.getMessage();

        Assertions.assertEquals(expectedMessageNullUserName,actualMessageNullUserName);
    }

    @Test
    @DisplayName("Register a valid user")
    public void registerAValidUserToTheContest() throws InvalidInputException, ContestProcessingCouldNotProceedException {

        iContestRepository=Mockito.mock(IContestRepository.class);
        ContestService contestService=new ContestService(iContestRepository);
        String contestId="1";
        Set<RegisteredUser> registeredUserSet=new HashSet<>();
        Contest contest=new Contest(contestId,registeredUserSet);
        List<Contest> contests=new ArrayList<>();
        contests.add(contest);
        UserInput userInput=new UserInput("Ajax","1");
        RegisteredUser userToBeRegistered=new RegisteredUser(userInput.getUsername(),userInput.getUserId());

        Mockito.when(iContestRepository.findAll()).thenReturn(contests);

       // Set<RegisteredUser> registeredUserSet=new HashSet<>();
        Contest updatedContest=new Contest("1",new HashSet<>(Arrays.asList(userToBeRegistered)));

        Mockito.when(iContestRepository.save(contest)).thenReturn(updatedContest);


        RegisteredUser savedRegisteredUser=contestService.registerUser(userInput);

        Assertions.assertEquals(savedRegisteredUser.getUserId(),"1");
        Assertions.assertEquals(savedRegisteredUser.getUsername(),"Ajax");
        Assertions.assertEquals(savedRegisteredUser.getScore(),0);
        Assertions.assertEquals(savedRegisteredUser.getBadges().size(),0);



    }


    @Test
    @DisplayName("Register a user which is already registered in the contest")
    public void registerAlreadyRegisteredUserToTheContest(){


        RegisteredUser registeredUser=new RegisteredUser("Ajax","1");
        String contestId="1";
        Set<RegisteredUser> registeredUserSet=new HashSet<>(Arrays.asList(registeredUser));
        Contest contest=new Contest(contestId,registeredUserSet);
        List<Contest> contests=new ArrayList<>();
        contests.add(contest);
        UserInput userInput=new UserInput("Ajax","1");

        iContestRepository=Mockito.mock(IContestRepository.class);
        ContestService contestService=new ContestService(iContestRepository);

        Mockito.when(iContestRepository.findAll()).thenReturn(contests);

        Exception exception=Assertions.assertThrows(ContestProcessingCouldNotProceedException.class,()->{
            contestService.registerUser(userInput);
        });

        String expectedMessage=exception.getMessage();
        String actualMessage="User "+userInput.getUsername()+" with id "+userInput.getUserId()+" is registered already";

        Assertions.assertEquals(expectedMessage,actualMessage);



    }



    @Test
    @DisplayName("Get registered users when no contest is being help")
    public void getRegisteredUserWhenNoContestIsBeingHeld(){

        iContestRepository= Mockito.mock(IContestRepository.class);
        ContestService contestService=new ContestService(iContestRepository);

        List<Contest> contests=new ArrayList<>();
        Mockito.when(iContestRepository.findAll()).thenReturn(contests);

        Exception exception=Assertions.assertThrows(ContestResourcesNotFoundException.class,()->{
            contestService.getRegisteredUsers();
        });


        String actualMessage="No contest is being held currently";
        String expectedMessage="No contest is being held currently";

        Assertions.assertEquals(expectedMessage,actualMessage);

    }





    @Test
    @DisplayName("Get all registered users participating in the contest")
    public void getAllRegisteredUsersInTheContest() throws ContestResourcesNotFoundException {

        RegisteredUser registeredUser=new RegisteredUser("Ajax","1");
        RegisteredUser registeredUser2=new RegisteredUser("Sierro","2");
        String contestId="1";
        Set<RegisteredUser> registeredUserSet=new HashSet<>(Arrays.asList(registeredUser,registeredUser2));
        Contest contest=new Contest(contestId,registeredUserSet);
        List<Contest> contests=new ArrayList<>();
        contests.add(contest);

        iContestRepository=Mockito.mock(IContestRepository.class);
        ContestService contestService=new ContestService(iContestRepository);

        Mockito.when(iContestRepository.findAll()).thenReturn(contests);

        List<RegisteredUser> registeredUsers=contestService.getRegisteredUsers();


        Assertions.assertEquals(2,registeredUsers.size());


        Assertions.assertEquals("1",registeredUsers.get(0).getUserId());
        Assertions.assertEquals("Ajax",registeredUsers.get(0).getUsername());
        Assertions.assertEquals(0,registeredUsers.get(0).getBadges().size());
        Assertions.assertEquals(0,registeredUsers.get(0).getScore());


        Assertions.assertEquals("2",registeredUsers.get(1).getUserId());
        Assertions.assertEquals("Sierro",registeredUsers.get(1).getUsername());
        Assertions.assertEquals(0,registeredUsers.get(1).getBadges().size());
        Assertions.assertEquals(0,registeredUsers.get(1).getScore());

    }

    @Test
    @DisplayName("Update user score with invalid scores")
    public void updateUserScoreWithInvalidScores(){

        iContestRepository=null;
        ContestService contestService=new ContestService(iContestRepository);

        Exception invalidUserIdException=Assertions.assertThrows(InvalidInputException.class,()->{
            contestService.setRegisteredUserScore("","1");
        });

        String invalidUserIdExceptionExpectedMessage=invalidUserIdException.getMessage();
        String invalidUserIdExceptionActualMessage="Invalid user with id ";

        Assertions.assertEquals(invalidUserIdExceptionExpectedMessage,invalidUserIdExceptionActualMessage);



        for(String newScore:Arrays.asList("0","-1","101","200")) {

            Exception invalidScoreException = Assertions.assertThrows(ContestProcessingCouldNotProceedException.class, () -> {
                contestService.setRegisteredUserScore("1", newScore);
            });

            String invalidScoreExceptionExpectedMessage = invalidScoreException.getMessage();
            String invalidScoreActualExceptionMessage = newScore + " is out of range ,score range should be between 1 and 100";


            Assertions.assertEquals(invalidScoreExceptionExpectedMessage, invalidScoreActualExceptionMessage);
        }

    }
}
