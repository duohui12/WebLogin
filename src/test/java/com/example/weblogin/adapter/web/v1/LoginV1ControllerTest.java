package com.example.weblogin.adapter.web.v1;

import com.example.weblogin.Exception.LoginFailException;
import com.example.weblogin.application.usecase.LoginUseCase;
import com.example.weblogin.application.usecase.SessionUseCase;
import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LoginV1Controller.class)
class LoginV1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginUseCase loginUseCase;

    @MockBean
    private SessionUseCase sessionUseCase;

    private String loginId = "duohui12@gmail.com";
    private String validPassword = "111111";
    private String invalidPassword = "000000";

    private LoginAccount loginAccount = LoginAccount.builder()
            .loginId(loginId)
            .password(validPassword)
            .build();
    private LoginAccount invalidLoginAccount = LoginAccount.builder()
            .loginId(loginId)
            .password(invalidPassword)
            .build();

    private Member member = Member.builder()
            .id(1L)
            .loginId(loginId)
            .password(validPassword)
            .name("dahye")
            .build();

    @BeforeEach
    void setup(){

        given(loginUseCase.login(any(LoginAccount.class)))
                .will(invocation -> {
                    LoginAccount loginAccount = invocation.getArgument(0);
                    if(loginAccount.getPassword().equals(validPassword)){
                        return member;
                    }else{
                        return null;
                    }
                });

        doNothing().when(sessionUseCase).createSession(any(Member.class),any(HttpServletResponse.class));
    }

    @Test
    void loginWithValidLoginForm() throws Exception {

        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("loginId",loginId)
                        .param("password",validPassword))
                .andExpect(status().isFound());

        verify(sessionUseCase).createSession(any(Member.class),any(HttpServletResponse.class));

        //TODO : reseponse sessionId 담은 쿠키 셋팅하는 방법 어떻게 테스트하는지 찾아봐야함..
    }


}
