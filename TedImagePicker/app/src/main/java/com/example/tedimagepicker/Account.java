package com.example.tedimagepicker;

import okhttp3.MultipartBody;

public class Account {
    private  String username;
    private String password;
    private byte[] avt;

    public Account(String username, String password, byte[] avt) {
        this.username = username;
        this.password = password;
        this.avt = avt;
    }

    public Account() {
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

    public byte[] getAvt() {
        return avt;
    }

    public void setAvt(byte[] avt) {
        this.avt = avt;
    }
}
