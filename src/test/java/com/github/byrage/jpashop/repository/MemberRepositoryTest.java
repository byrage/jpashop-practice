package com.github.byrage.jpashop.repository;

import com.github.byrage.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void name() {
        Member member = new Member();
        member.setName("hi");

        Long savedId = memberRepository.createMember(member);

        assertThat(memberRepository.findById(savedId)).isEqualTo(member);
    }
}