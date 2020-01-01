package com.github.byrage.jpashop.service;

import com.github.byrage.jpashop.domain.item.Item;
import com.github.byrage.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(Item item) {
        Item findItem = itemRepository.findOne(item.getId());
        findItem.setName(item.getName());
        findItem.setPrice(item.getPrice());
        findItem.setStockQuantity(item.getStockQuantity());
        return findItem;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
