package com.github.byrage.jpashop.repository;

import com.github.byrage.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }

//    public List<Order> findAll(OrderSearch orderSearch){}
}
