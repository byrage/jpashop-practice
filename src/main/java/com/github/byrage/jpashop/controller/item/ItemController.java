package com.github.byrage.jpashop.controller.item;

import com.github.byrage.jpashop.domain.item.Book;
import com.github.byrage.jpashop.domain.item.Item;
import com.github.byrage.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", BookForm.empty());
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(BookForm form) {
        itemService.save(form.convertToEntity());
        return "redirect:/items";
    }

    @GetMapping
    public String list(Model model) {
        List<BookView> bookViews = itemService.findAll()
                .stream()
                .map(item -> (Book) item)
                .map(BookView::from)
                .collect(Collectors.toList());
        model.addAttribute("items", bookViews);

        return "items/itemList";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        model.addAttribute("form", item);

        return "items/updateItemForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, BookForm bookForm) {
        Item shouldEditItem = bookForm.convertToEntity(itemId);
        itemService.update(shouldEditItem);
        return "redirect:/items";
    }
}
