package com.example.weblogin.application.usecase;

import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;

public interface AuthenticationUseCase {
    public String login(LoginAccount loginAccount);
}
