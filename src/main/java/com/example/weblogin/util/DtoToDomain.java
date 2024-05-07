package com.example.weblogin.util;

import com.example.weblogin.adapter.web.JoinForm;
import com.example.weblogin.adapter.web.LoginForm;
import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;

public abstract class DtoToDomain {
    public static LoginAccount LoginFormToDomain(LoginForm loginForm){
        if(loginForm == null) return null;

        return LoginAccount.builder()
                .loginId(loginForm.getLoginId())
                .password(loginForm.getPassword())
                .build();
    }

    public static Member JoinFormToDomain(JoinForm joinForm){
        if(joinForm == null) return null;

        return Member.builder()
                .loginId(joinForm.getLoginId())
                .password(joinForm.getPassword())
                .name(joinForm.getName())
                .build();
    }
}
