package com.github.byrage.jpashop.service;

import com.github.byrage.jpashop.domain.Member;
import com.github.byrage.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Member member) {
        validateDuplicate(member);
        memberRepository.createMember(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    private void validateDuplicate(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if (!CollectionUtils.isEmpty(findMember)) {
            throw new IllegalStateException("duplicate member");
        }
    }
}
