package com.example.weblogin.Exception;

public class DuplicatedIdException extends RuntimeException{
    public DuplicatedIdException(String loginId){
        super("This id is already existed : " + loginId);
    }
}
