package com.example.weblogin.application;

import com.example.weblogin.application.port.FindMemberPort;
import com.example.weblogin.application.usecase.FindMemberUseCase;
import com.example.weblogin.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMemberService implements FindMemberUseCase {

    private final FindMemberPort findMemberPort;

    @Override
    public Optional<Member> findMemberByLoginId(String loginId) {
        return findMemberPort.findByLoginId(loginId);
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        return findMemberPort.findById(id);
    }
}
