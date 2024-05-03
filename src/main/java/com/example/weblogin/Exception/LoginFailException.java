package com.example.weblogin.Exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String email){
        super("Login Fail : " + email);
    }
}
