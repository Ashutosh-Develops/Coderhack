package com.github.machinecodingchallenge.coderhack.dto;

import java.util.HashSet;
import java.util.Set;

import com.github.machinecodingchallenge.coderhack.constant.Badge;

public class RegisteredUser {

  private String username;
  private String userId;
  private Set<Badge> badges;
  private int score;

  public RegisteredUser(String username, String userId) {
    
    this.username = username;
    this.userId = userId;
    this.badges = new HashSet<>();
    this.score = 0;
  }

  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
    RegisteredUser other = (RegisteredUser) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }


  public int getScore() {
    return score;
  }

  public void setScore(int score) {

    if(score<=0||score>100){
      throw new RuntimeException("Invalid score");
    }

    this.score = score;

    if(score>=1&score<30){
       addBadge(Badge.CODE_NINJA);
    }else if(score>=30&score<60){
      addBadge(Badge.CODE_CHAMP);
    }else{
      addBadge(Badge.CODE_MASTER);
    }


  }

  public String getUsername() {
    return username;
  }

  public String getUserId() {
    return userId;
  }

  public Set<Badge> getBadges() {
    return badges;
  }
  
  private void addBadge(Badge badge){
     badges.add(badge);
  }


}

  
  


  


  

