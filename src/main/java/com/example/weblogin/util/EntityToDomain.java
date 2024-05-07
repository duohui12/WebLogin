package com.example.weblogin.util;

import com.example.weblogin.adapter.persistence.MemberEntity;
import com.example.weblogin.domain.Member;

public abstract class EntityToDomain {
    public static Member MemberEntityToDomain(MemberEntity memberEntity){
        if(memberEntity == null) return null;

        return Member.builder()
                .id(memberEntity.getId())
                .loginId(memberEntity.getLoginId())
                .name(memberEntity.getName())
                .password(memberEntity.getPassword())
                .build();
    }
}
