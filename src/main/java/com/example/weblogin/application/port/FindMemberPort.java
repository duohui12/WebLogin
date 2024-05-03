package com.example.weblogin.application.port;

import com.example.weblogin.domain.Member;

import java.util.List;
import java.util.Optional;

public interface FindMemberPort {

    public Optional<Member> findById(Long id);

    public Optional<Member> findByLoginId(String loginId);

    public List<Member> findAll();

}
