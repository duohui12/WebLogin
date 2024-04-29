package com.example.weblogin.domain.member;

import com.example.weblogin.web.Exception.DuplicatedUserEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;

    public Member join(Member member){

        if(memberRepository.findByLoginId(member.getLoginId()).isPresent()){
            throw new DuplicatedUserEmailException(member.getLoginId());
        }

        return memberRepository.save(member);
    }

}
