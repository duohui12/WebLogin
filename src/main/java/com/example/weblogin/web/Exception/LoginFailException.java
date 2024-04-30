package com.example.weblogin.web.Exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String email){
        super("Login Fail : " + email);
    }
}
