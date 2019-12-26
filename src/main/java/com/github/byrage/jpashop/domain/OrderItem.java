package com.github.byrage.jpashop.domain;

import com.github.byrage.jpashop.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private long orderPrice;

    private long count;

    public void cancel() {
        this.item.addStockQuantity(this.count);
    }

    public static OrderItem createOrderItem(Item item, long orderPrice, long count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.cutStockQuantity(count);

        return orderItem;
    }

    public long getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
