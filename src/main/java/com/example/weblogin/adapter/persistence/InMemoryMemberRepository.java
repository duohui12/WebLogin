package com.example.weblogin.adapter.persistence;

import com.example.weblogin.application.port.FindMemberPort;
import com.example.weblogin.application.port.SaveMemberPort;
import com.example.weblogin.domain.Member;
import com.example.weblogin.util.DomainToEntity;
import com.example.weblogin.util.EntityToDomain;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
class InMemoryMemberRepository implements SaveMemberPort
                                                , FindMemberPort {
    private static Map<Long, MemberEntity> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {

        member.setId(getId());
        store.put(member.getId(), DomainToEntity.MemberToEntity(member));

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        MemberEntity memberEntity = store.get(id);
        return Optional.ofNullable(EntityToDomain.MemberEntityToDomain(memberEntity));
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        List<MemberEntity> memberEntities = new ArrayList<>(store.values());

        return memberEntities.stream()
                .map( memberEntity -> EntityToDomain.MemberEntityToDomain(memberEntity))
                .collect(Collectors.toList());
    }

    public void clearStore(){
        store.clear();
    }

    private synchronized Long getId(){
        sequence += 1L;
        return sequence;
    }
}
