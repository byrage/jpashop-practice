package com.github.byrage.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Item{
    private String director;
    private String actor;
}
