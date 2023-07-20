
package com.andycode.libreria.service;

import com.andycode.libreria.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    //defino los methodos
    Author save(Author author);
    List<Author> getAuthors(); //solo lista sin paginar
    Page<Author> getAuthors(Pageable pageable);
    Optional<Author> getAuthorById(int id);
    void deleteAuthorById(int id);
    List<Author> authorSearch(String name) throws Exception;
}
