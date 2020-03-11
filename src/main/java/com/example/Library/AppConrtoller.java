/**
 * Created by Bogoslovskiy Denis 2020
 */
package com.example.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
    public String showNewBookPage(Model model){
        Book book = new Book();
        model.addAttribute("book", book);

        return "new_book";

    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String  saveBook(@ModelAttribute("book") Book book){
        service.save(book);
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditBookPage(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("edit_book");
        Book book  = service.get(id);
        modelAndView.addObject("book", book);
        return modelAndView;

    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id")Long id){
        service.delete(id);

        return "redirect:/";
    }

}
