package com.majdamireh.resvsystem;

public class UserCredentials {
    private String userName;
    private String email;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }



    public String getPassword() {
        return password;
    }

    public UserCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
