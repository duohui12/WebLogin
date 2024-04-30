package com.example.weblogin.web.login;

import com.example.weblogin.domain.login.LoginService;
import com.example.weblogin.domain.member.Member;
import com.example.weblogin.web.Exception.LoginFailException;
import com.example.weblogin.web.session.SessionManager;
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


@WebMvcTest(SessionLoginController.class)
class SessionLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private SessionManager sessionManager;

    private String loginId = "duohui12@gmail.com";
    private String validPassword = "111111";
    private String invalidPassword = "000000";

    private Member member = Member.builder()
                                .loginId(loginId)
                                .password(validPassword)
                                .build();

    @BeforeEach
    void setup(){
        given(loginService.login(loginId,validPassword))
                .willReturn(member);

        given(loginService.login(loginId, invalidPassword))
                .willThrow(new LoginFailException(member.getLoginId()));

        doNothing().when(sessionManager).createSession(any(Member.class),any(HttpServletResponse.class));
    }

    @Test
    void loginWithValidLoginForm() throws Exception {

        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("loginId",loginId)
                        .param("password",validPassword));

        verify(sessionManager).createSession(any(Member.class),any(HttpServletResponse.class));

        //TODO : reseponse sessionId 담은 쿠키 셋팅하는 방법 어떻게 테스트하는지 찾아봐야함..
    }


}
