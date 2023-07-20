package com.andycode.libreria.controller;

import com.andycode.libreria.model.Book;
import com.andycode.libreria.service.BookServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //para manejar todas las apirest
@RequestMapping("/api/v1/book") //establezco enpoint/
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestBody Book book) throws Exception {
        Book bookSave = bookServiceImpl.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookSave);
    }

    //Metodo para listar libros
    /*@GetMapping("/list")
   public ResponseEntity<List<Book>> listBook() { // Para listar todos los libros
        List<Book> books = bookServiceImpl.getBooks();//este  metodo listaba todos los libros
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }*/
    
    @GetMapping("/list/{page}")
    public ResponseEntity<Page<Book>> listBook(Pageable pageable) {
        Page<Book> books = bookServiceImpl.getBooks(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        Optional<Book> miBook = bookServiceImpl.getBookById(id);
        if (miBook.isPresent()) {
            return new ResponseEntity<>(miBook.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) throws Exception {

        Optional<Book> miBook = bookServiceImpl.getBookById(id);
        if (miBook.isPresent()) {
            book.setId(id);
            Book updateBook = bookServiceImpl.saveBook(book);
            return ResponseEntity.status(HttpStatus.OK).body(updateBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Integer id) throws Exception {

        Optional<Book> miBook = bookServiceImpl.getBookById(id);
        Map<String, String> response = new HashMap<>();

        if (miBook.isPresent()) {
            bookServiceImpl.deleteBookById(id);
            response.put("message", "book deleted successfully");
            return ResponseEntity.ok().body(response);
        } 
        else {
            response.put("message", "The book is not registered in the database");
            return ResponseEntity.ok().body(response);
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchTilte(@PathVariable String title) throws Exception {
        try {
            List<?> books = bookServiceImpl.searchTitle(title);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{'error':'" + e.getMessage() + "'}"));
        }
    }
}
