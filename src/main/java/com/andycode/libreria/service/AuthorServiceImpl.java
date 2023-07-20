package com.andycode.libreria.service;

import com.andycode.libreria.api.AuthorRepository;
import com.andycode.libreria.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//implemento la interface
@Service //esta clase es un servicio
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository; //hago la inyeccion de la interface repositorio

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    
    @Override
    public List<Author> getAuthors() { //Este metodo listaba autores sin paginar
        return authorRepository.findAll(); //obtengo la lista de autores
    }
  
    @Override
    public Page<Author> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> authorSearch(String name) throws Exception {
        try {
            return authorRepository.searchAuthor(name);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
