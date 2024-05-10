package com.example.weblogin.application;

import com.example.weblogin.Exception.LoginFailException;
import com.example.weblogin.application.port.FindMemberPort;
import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;
import com.example.weblogin.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final FindMemberPort findMemberPort;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginAccount loginAccount) {
        Member member = findMemberPort.findByLoginId(loginAccount.getLoginId())
                .filter(m -> m.getPassword().equals(loginAccount.getPassword()))
                .orElseThrow(() -> new LoginFailException(loginAccount.getLoginId()));

        return jwtUtil.encode(member.getId());  //토큰 반환
    }

}
