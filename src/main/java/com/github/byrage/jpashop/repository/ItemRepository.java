package com.github.byrage.jpashop.repository;

import com.github.byrage.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }
}
