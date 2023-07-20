package com.andycode.libreria.service;

import com.andycode.libreria.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    //defino los methodos
    Book saveBook(Book book) throws Exception;
    //List<Book> getBooks();
    //Se va paginar para no sobrecargar la pagina de informacion
    Page<Book> getBooks(Pageable pageable);
    Optional<Book> getBookById(int id);
    void deleteBookById(int id) throws Exception;
    List<Book> findByTitleContaining(String title) throws Exception;
    List<Book> searchTitle(String title) throws Exception;
}
