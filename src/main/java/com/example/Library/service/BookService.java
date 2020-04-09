/**
 * Created by Bogoslovskiy Denis 2020
 */
package com.example.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private  BookRepository repository;

    public List<Book> listAll(){
        return repository.findAll();
    }
    public List<Book> findByBookTitle(String title){
        return repository.findByBooktitle(title);
    }
    public void save(Book book){
        repository.save(book);
    }
    public Book get(Long id){
        return repository.findById(id).get();
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}
