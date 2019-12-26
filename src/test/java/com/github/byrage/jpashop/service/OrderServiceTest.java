package com.github.byrage.jpashop.service;

import com.github.byrage.jpashop.domain.*;
import com.github.byrage.jpashop.domain.item.Book;
import com.github.byrage.jpashop.exception.NotEnoughStockQuantityException;
import com.github.byrage.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("상품주문")
    void order() {
        Member member = createMember();
        Book book = createBook(10);
        long orderCount = 2L;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        assertThat(orderRepository.findOne(orderId))
                .satisfies(order -> {
                    assertThat(order.getMember()).isEqualTo(member);
                    assertThat(order.getOrderItems()).extracting(OrderItem::getItem).containsOnlyOnce(book);
                    assertThat(order.getOrderItems()).extracting(OrderItem::getCount).containsOnly(orderCount);
                });
    }

    @Test
    @DisplayName("주문취소")
    void cancelOrder() {
        int stockQuantity = 10;
        Member member = createMember();
        Book book = createBook(stockQuantity);
        long orderCount = 2L;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(orderId);

        assertThat(orderRepository.findOne(orderId).getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(stockQuantity);
    }

    @Test
    @DisplayName("재고수량 초과")
    void order_exceedOrderQuantity() {
        Member member = createMember();
        Book item = createBook(10);
        int orderCount = 10000;

        assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockQuantityException.class);
    }

    private Book createBook(int stockQuantity) {
        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(Address.of("서울", "통신", "123213"));
        em.persist(member);
        return member;
    }
}