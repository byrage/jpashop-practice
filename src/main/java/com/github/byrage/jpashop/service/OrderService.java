package com.github.byrage.jpashop.service;

import com.github.byrage.jpashop.domain.*;
import com.github.byrage.jpashop.domain.item.Item;
import com.github.byrage.jpashop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, long count){

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
          return orderRepository.findByOrderSearch(orderSearch);
    }
}
