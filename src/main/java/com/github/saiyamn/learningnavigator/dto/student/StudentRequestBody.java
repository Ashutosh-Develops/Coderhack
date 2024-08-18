package com.github.saiyamn.learningnavigator.dto.student;

public class StudentRequestBody {

    private String userName;

    public StudentRequestBody() {
    }

    public StudentRequestBody(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
