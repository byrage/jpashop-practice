package com.github.byrage.jpashop.repository;

import com.github.byrage.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private static final Class<Member> MEMBER_CLASS = Member.class;
    private final EntityManager em;

    public Long createMember(Member member) {
        em.persist(member);
        return member.getId();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", MEMBER_CLASS)
                .getResultList();
    }

    public Member findById(Long memberId) {
        return em.find(MEMBER_CLASS, memberId);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", MEMBER_CLASS)
                .setParameter("name", name)
                .getResultList();
    }
}
