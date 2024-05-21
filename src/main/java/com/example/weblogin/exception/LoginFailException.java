package com.example.weblogin.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String loginId){
        super("Login Fail : " + loginId);
    }
}
