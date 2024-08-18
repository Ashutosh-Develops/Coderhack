package com.github.saiyamn.learningnavigator.dto.student;

public class AddStudentDTO {

    private String userName;

    public AddStudentDTO(){

    }

    public AddStudentDTO(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
