package com.laca.user;

public class User {
    private int userID;
    private String name;
    private String identificationNumber;
    private String companyName;
    private String userType;
    private String password;


    public User (){}
    public User(int userID, String name, String identificationNumber, String companyName, String userType, String password) {
        this.userID = userID;
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.companyName = companyName;
        this.userType = userType;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String type) {
        if(type.equalsIgnoreCase("Carrier")){
            userType = String.valueOf(UserType.CARRIER);
        }else if (type.equalsIgnoreCase("Client")){
            userType = String.valueOf(UserType.CLIENT);
        }else if (type.equalsIgnoreCase("Administrator")){
            userType = String.valueOf(UserType.ADMINISTRATOR);
        }else if (type.equalsIgnoreCase("Approver")){
            userType = String.valueOf(UserType.APPROVER);
        }else {
            userType = String.valueOf(UserType.VIEWER);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", UserType=" + userType +
                ", password='" + password + '\'' +
                '}';
    }
}

