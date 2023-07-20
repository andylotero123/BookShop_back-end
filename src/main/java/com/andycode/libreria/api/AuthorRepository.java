
package com.andycode.libreria.api;

import com.andycode.libreria.model.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
    
    
    @Query(value = "SELECT a FROM Author a WHERE a.name like %?1%") //Query hecha hacia el Modelo %?%: sigifica qyue el primer atributo que se envia es el nombre del author
    List<Author> searchAuthor(String name);
    
}
