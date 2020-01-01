package com.github.byrage.jpashop.controller.member;

import com.github.byrage.jpashop.domain.Address;
import com.github.byrage.jpashop.domain.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberForm {

    @NotEmpty(message = "회원가입을 하기 위해서 멤버이름은 필수입니다.")
    private String name;
    private String city;
    private String street;
    private String zipCode;

    public static MemberForm empty() {
        return new MemberForm();
    }

    public Member convertToEntity() {
        Member member = new Member();
        member.setName(this.getName());
        member.setAddress(Address.of(this.getCity(), this.getStreet(), this.getZipCode()));
        return member;
    }
}
