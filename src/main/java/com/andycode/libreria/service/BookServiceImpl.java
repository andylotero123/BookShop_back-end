 package com.andycode.libreria.service;

import com.andycode.libreria.api.BookRepository;
import com.andycode.libreria.model.Book;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional//Se usa cuando se modifica la base datos y si ocurre un error, este se sepa, ej: se cayó el servidor, caida de internet, etc
    public Book saveBook(Book book) throws Exception {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Esta metodo esra para listar libros
    //@Override
    //public List<Book> getBooks() {
    //    return bookRepository.findAll();
    //}
    //Metodo para paginar el metodo de listar libros de manera controlada
    @Override
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteBookById(int id) throws Exception {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    
    @Override
    public List<Book> findByTitleContaining(String title) throws Exception {
        try {
            return bookRepository.findByTitleContaining(title);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Book> searchTitle(String title) throws Exception {
        try {
            return bookRepository.searchTitle(title);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
