package com.example.weblogin.util;

import com.example.weblogin.adapter.persistence.MemberEntity;
import com.example.weblogin.adapter.web.LoginForm;
import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;

public abstract class DomainToEntity {

    public static MemberEntity MemberToEntity(Member member){
        if(member == null) return null;

        return MemberEntity.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .password(member.getPassword())
                .build();
    }

}
