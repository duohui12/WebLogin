package com.example.weblogin.util;

import com.example.weblogin.adapter.persistence.MemberEntity;
import com.example.weblogin.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EntityToDomainTest {

    @Test
    void NullMemberEntityToDomain(){
        MemberEntity memberEntity = null;

        assertThat(EntityToDomain.MemberEntityToDomain(memberEntity))
                .isEqualTo(null);

        //assertThatThrownBy(() -> EntityToDomain.MemberEntityToDomain(memberEntity))
        //        .isInstanceOf(NullPointerException.class);
    }

    @Test
    void MemberEntityToDomain(){
        MemberEntity memberEntity = MemberEntity.builder()
                .id(1L)
                .loginId("dh1234@gmail.com")
                .name("duohui")
                .password("1111")
                .build();

        Member member = EntityToDomain.MemberEntityToDomain(memberEntity);

        assertThat(member.getId()).isEqualTo(memberEntity.getId());
        assertThat(member.getName()).isEqualTo(memberEntity.getName());
        assertThat(member.getLoginId()).isEqualTo(memberEntity.getLoginId());
        assertThat(member.getPassword()).isEqualTo(memberEntity.getPassword());

    }

}
