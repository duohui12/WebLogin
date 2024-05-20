package com.example.weblogin.application.usecase;

import com.example.weblogin.domain.Member;

import java.util.Optional;

public interface FindMemberUseCase {

    public Optional<Member> findMemberByLoginId(String loginId);

    public Optional<Member> findMemberById(Long id);

}
