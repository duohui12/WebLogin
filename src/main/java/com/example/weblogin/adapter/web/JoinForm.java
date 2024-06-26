package com.example.weblogin.adapter.web;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinForm {

    @NotEmpty
    private String loginId; //로그인 ID

    @NotEmpty
    private String name; //사용자 이름

    @NotEmpty
    private String password;

}
