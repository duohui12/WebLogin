package com.example.weblogin.application;

import com.example.weblogin.application.port.FindMemberPort;
import com.example.weblogin.application.usecase.JoinUseCase;
import com.example.weblogin.domain.Member;
import com.example.weblogin.Exception.DuplicatedIdException;
import com.example.weblogin.application.port.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class JoinService implements JoinUseCase {

    private final FindMemberPort findMemberPort;
    private final SaveMemberPort saveMemberPort;


    public Member join(Member member){

        if(findMemberPort.findByLoginId(member.getLoginId()).isPresent()){
            throw new DuplicatedIdException(member.getLoginId());
        }

        return saveMemberPort.save(member);
    }

}
