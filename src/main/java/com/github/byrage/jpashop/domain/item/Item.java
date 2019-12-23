package com.github.byrage.jpashop.domain.item;

import com.github.byrage.jpashop.domain.Category;
import com.github.byrage.jpashop.exception.NotEnoughStockQuantityException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private long price;

    private long stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * 재고 추가
     */
    public void addStockQuantity(long stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    /**
     * 재고 삭감
     */
    public void cutStockQuantity(long stockQuantity) {

        long restStockQuantity = this.stockQuantity - stockQuantity;
        if (restStockQuantity < 0) {
            throw new NotEnoughStockQuantityException();
        }
        this.stockQuantity -= stockQuantity;
    }
}
