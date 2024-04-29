package com.example.weblogin.web.Exception;

public class DuplicatedUserEmailException extends RuntimeException{
    public DuplicatedUserEmailException(String email){
        super("User email is already existed : " + email);
    }
}
