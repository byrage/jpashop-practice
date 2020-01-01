package com.github.byrage.jpashop.controller.member;

import com.github.byrage.jpashop.domain.Address;
import com.github.byrage.jpashop.domain.Member;
import lombok.Getter;

@Getter
public class MemberView {

    private Long id;
    private String name;
    private Address address;

    public static MemberView from(Member member) {
        MemberView memberView = new MemberView();
        memberView.id = member.getId();
        memberView.name = member.getName();
        memberView.address = member.getAddress();
        return memberView;
    }
}
