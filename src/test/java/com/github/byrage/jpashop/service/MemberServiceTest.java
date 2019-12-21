package com.github.byrage.jpashop.service;

import com.github.byrage.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void join() {
        Member member = new Member();
        member.setName("ywchoi");

        Long savedId = memberService.join(member);

        assertThat(memberService.findOne(savedId)).isEqualTo(member);
    }

    @Test
    void duplicate() {
        Member member1 = new Member();
        member1.setName("ywchoi");
        Member member2 = new Member();
        member2.setName("ywchoi");

        memberService.join(member1);
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }
}