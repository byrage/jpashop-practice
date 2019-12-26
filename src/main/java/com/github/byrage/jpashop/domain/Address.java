package com.github.byrage.jpashop.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class Address {
    private String city;
    private String street;
    private String zipCode;
}
