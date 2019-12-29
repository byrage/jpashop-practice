package com.github.byrage.jpashop.repository;

import com.github.byrage.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private static final int MAX_RESULT = 1000;
    private final EntityManager em;

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findByOrderSearch(OrderSearch orderSearch) {
        return em.createQuery("select o from Order o join o.member m"
                + "where o.status = :status"
                + "and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("nmae", orderSearch.getMemberName())
                .setMaxResults(MAX_RESULT)
                .getResultList();
    }
}
