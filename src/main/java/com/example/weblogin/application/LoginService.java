package com.example.weblogin.application;

import com.example.weblogin.exception.LoginFailException;
import com.example.weblogin.application.port.FindMemberPort;
import com.example.weblogin.application.usecase.LoginUseCase;
import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LoginService implements LoginUseCase {

    private final FindMemberPort findMemberPort;

    public Member login(LoginAccount loginAccount){

        return findMemberPort.findByLoginId(loginAccount.getLoginId())
                .filter(m -> m.getPassword().equals(loginAccount.getPassword()))
                .orElseThrow(() -> new LoginFailException(loginAccount.getLoginId()));
    }

}
