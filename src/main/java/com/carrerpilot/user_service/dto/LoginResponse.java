package com.carrerpilot.user_service.dto;

public class LoginResponse {

    private int id;
    private String token;
    private String tokenType;
    private String email;

    public LoginResponse() {
    }

    public LoginResponse(int id, String token, String tokenType, String email) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}