package com.example.weblogin.adapter.persistence;

import com.example.weblogin.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class InMemoryMemberRepositoryTest {

    private InMemoryMemberRepository inMemoryMemberRepository;

    @BeforeEach
    void setup(){
        inMemoryMemberRepository = new InMemoryMemberRepository();
    }

    @Test
    void findByNotExistId(){
        Optional<Member> member = inMemoryMemberRepository.findById(2L);
        assertThat(member.isEmpty()).isTrue();
    }

    @Test
    void findByExistingId(){
        Member savedMember = inMemoryMemberRepository.save( Member.builder()
                                                                    .loginId("dh12@gmail.com")
                                                                    .name("duohui")
                                                                    .password("1111")
                                                                    .build() );

        Optional<Member> foundMember = inMemoryMemberRepository.findById(savedMember.getId());

        assertThat(foundMember.isPresent()).isTrue();
        assertThat(foundMember.get().getId()).isEqualTo(savedMember.getId());
        assertThat(foundMember.get().getLoginId()).isEqualTo(savedMember.getLoginId());
        assertThat(foundMember.get().getName()).isEqualTo(savedMember.getName());
        assertThat(foundMember.get().getPassword()).isEqualTo(savedMember.getPassword());
    }

    @Test
    void findAllWithEmptyRepository() {
        List<Member> memberList = inMemoryMemberRepository.findAll();

        assertThat(memberList).isNotEqualTo(null);
        assertThat(memberList).isEmpty();
        assertThat(memberList.size()).isEqualTo(0);
    }


}
