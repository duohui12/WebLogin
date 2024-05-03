package com.example.weblogin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginAccount {

    private String loginId;

    private String password;
}

