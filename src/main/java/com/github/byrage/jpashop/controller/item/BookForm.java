package com.github.byrage.jpashop.controller.item;

import com.github.byrage.jpashop.domain.item.Book;
import com.github.byrage.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;
    private String name;
    private long price;
    private long stockQuantity;
    private String author;
    private String isbn;

    public static BookForm empty() {
        return new BookForm();
    }

    public Item convertToEntity() {
        return convertToEntity(null);
    }

    public Item convertToEntity(Long itemId) {
        Book book = new Book();
        book.setId(itemId);
        book.setName(this.getName());
        book.setPrice(this.getPrice());
        book.setStockQuantity(this.getStockQuantity());
        book.setAuthor(this.getAuthor());
        book.setIsbn(this.getIsbn());
        return book;
    }
}
