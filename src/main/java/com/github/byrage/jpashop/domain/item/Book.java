package com.github.byrage.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("BOOK")
public class Book extends Item {

    private String author;
    private String isbn;
}
