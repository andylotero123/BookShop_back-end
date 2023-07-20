
package com.andycode.libreria.api;

import com.andycode.libreria.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>{
    
    List<Book> findByTitleContaining(String title); //forma 1 de busqueda
    

    //@Query(value = "SELECT * FROM bookshop WHERE title like %?1% OR description like %?2%", nativeQuery = true) para consultar por algun de los 2 parametros 
    //NOTA: Cuando defino que la consulta es nativa con la palabra nativeQuery = true, la consulta se realiza en la tabla
    //@Query(value = "SELECT * FROM book WHERE title like %?1%", nativeQuery = true) //Forma 2 de busqueda

    
    //NOTA: cuando no se le coloca nativeQuery, entonces la consulta se realiza en el modelo
    @Query(value = "SELECT b FROM Book b WHERE b.title like %?1%") //Consulta hecha en el modelo Book por titulo del libro, donde b es el alias de Book, con esta consulta se obtiene tambien el autor y devuelve un objeto estructurado
    List<Book> searchTitle(String title);
    
    //Nota con author se puede hacer una consulta nativa, porque este si puede traer todos los datos del author sin hacer anidamientos de los objetos
}
