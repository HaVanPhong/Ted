package com.example.tedimagepicker;

public class AccountBody {

    private String username;
    private String password;
    private String avt;

    public AccountBody() {
    }

    public AccountBody(String username, String password, String avt) {
        this.username = username;
        this.password = password;
        this.avt = avt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }
}
