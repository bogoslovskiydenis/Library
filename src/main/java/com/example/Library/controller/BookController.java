/**
 * Created by Bogoslovskiy Denis 2020
 */
package com.example.Library.controller;

import com.example.Library.domain.Book;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;

        setupTestData();
    }

    private void setupTestData() {
        for (long i = 0; i < 10; i++) {
            service.save(new Book(i, "title" + i, "author" + i, (int)i, "discipline" + i));
        }
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String showBooks(Model model, @RequestParam(required = false) String title) {
        List<Book> listBook = null;

        if (title != null) {
            listBook = service.findByBookTitle(title);
        } else {
            listBook = service.listAll();
        }
        model.addAttribute("listBook", listBook);

        return "books";
    }

    @RequestMapping("/new")
    public String showNewBookPage(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);

        return "new_book";

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book) {
        service.save(book);
        return "redirect:/books";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditBookPage(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_book");

        try {
            Book book = service.get(id);
            modelAndView.addObject("book", book);
        } catch (EntityNotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            modelAndView.setViewName("404");
        }

        return modelAndView;

    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/books";
    }

}
