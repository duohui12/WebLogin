package com.example.weblogin.application.usecase;

import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;

public interface LoginUseCase {
    public Member login(LoginAccount loginAccount);
}
