package com.learn.entity;

public class User {
    private String userName;
    private String userPassword;
    private String userGender;
    private int userAge;
    private String userAddress;
    private String userBirthday;
    private String pictureName;

    public User() {
    }
    public User(String userPassword, String userGender, int userAge, String userAddress, String userBirthday, String pictureName) {
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userAddress = userAddress;
        this.userBirthday = userBirthday;
        this.pictureName = pictureName;
    }
    public User(String userName, String userPassword, String userGender, int userAge, String userAddress, String userBirthday, String pictureName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userAddress = userAddress;
        this.userBirthday = userBirthday;
        this.pictureName = pictureName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAge=" + userAge +
                ", userAddress='" + userAddress + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", pictureName='" + pictureName + '\'' +
                '}';
    }
}
