/**
 * Created by Bogoslovskiy Denis 2020
 */
package com.example.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppConrtoller {
    @Autowired
    private BookService service;
    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Book> listBook = service.listAll();
        model.addAttribute("listBook", listBook);

        return "home";
    }
    @RequestMapping("/new")
    public String showNewBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);

        return "new_book";

    }
}
