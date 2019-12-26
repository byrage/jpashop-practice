package com.github.byrage.jpashop.domain;

import com.github.byrage.jpashop.util.LocalDateTimeUtils;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        Arrays.stream(orderItems).forEach(order::addOrderItems);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDateTime(LocalDateTimeUtils.now());

        return order;
    }

    public void cancel() {
        if (DeliveryStatus.COMP == delivery.getStatus()) {
            throw new IllegalStateException("배송 시작된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        this.orderItems.forEach(OrderItem::cancel);
    }

    public long getTotalPrice() {
        return this.orderItems.stream().mapToLong(OrderItem::getTotalPrice).sum();
    }
}
