package com.example.weblogin.Exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String loginId){
        super("Login Fail : " + loginId);
    }
}
