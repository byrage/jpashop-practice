package com.github.byrage.jpashop.controller.item;

import com.github.byrage.jpashop.domain.item.Book;
import lombok.Getter;

@Getter
public class BookView {

    private Long id;
    private String name;
    private long price;
    private long stockQuantity;
    private String author;
    private String isbn;

    public static BookView from(Book item) {
        BookView bookView = new BookView();
        bookView.id = item.getId();
        bookView.name = item.getName();
        bookView.price = item.getPrice();
        bookView.stockQuantity = item.getStockQuantity();
        bookView.author = item.getAuthor();
        bookView.isbn = item.getIsbn();
        return bookView;
    }
}
