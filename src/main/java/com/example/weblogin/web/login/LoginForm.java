package com.example.weblogin.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
