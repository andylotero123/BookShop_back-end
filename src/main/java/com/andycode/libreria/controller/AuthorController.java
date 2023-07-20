package com.andycode.libreria.controller;

import com.andycode.libreria.model.Author;
import com.andycode.libreria.service.AuthorServiceImpl;
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
@RequestMapping("/api/v1/author") //establezco enpoint/
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<Author> save(@RequestBody Author author) {

        Author authorSave = authorServiceImpl.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorSave);
    }

    
    @GetMapping("/list") //Se usa para obtener los autores, para luegos deplegarlos en select del frontEnd
    public ResponseEntity<List<Author>> getAuthors() { //Para listar authors sin paginar
        List<Author> listAuthors = authorServiceImpl.getAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(listAuthors);
    }
    
    @GetMapping("/list/{page}")
    public ResponseEntity<Page<Author>> listAuthor(Pageable pageable) {//para listar authors de manera paginada
        Page<Author> authors = authorServiceImpl.getAuthors(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }
/*
    @GetMapping("/list")
    public ResponseEntity<Page<Author>> listAuthorA(Pageable pageable) {//para listar authors de manera paginada
        Page<Author> authors = authorServiceImpl.getAuthors(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }
*/
    @GetMapping("/findById/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Integer id) {

        Optional<Author> getAuthorId = authorServiceImpl.getAuthorById(id);

        //Map<String, Object> map = new HashMap<>();
        //map.put("author", getAuthorId.get());
        if (getAuthorId.isPresent()) {
            System.out.println(getAuthorId);
            System.out.println(getAuthorId.get());
            //return new ResponseEntity<>(getAuthorId.get(), HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(getAuthorId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchAuthor/{name}")
    public ResponseEntity<?> search(@PathVariable String name) throws Exception {

        Map<String, String> response = new HashMap<>();
        try {
            List<?> authors = authorServiceImpl.authorSearch(name);
            return ResponseEntity.ok(authors);
        } catch (Exception e) {
            //response.put("message", "Author no registrado en la base de datos");
            //return ResponseEntity.ok(response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'error'}");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
        Optional<Author> saveAuthor = authorServiceImpl.getAuthorById(id); //me selecciona un objeto, un author por id

        if (saveAuthor.isPresent()) { //si author tiene un dato
            author.setId(id);
            Author updateAuthor = authorServiceImpl.save(author);
            return ResponseEntity.ok().body(updateAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteAuthorById(@PathVariable Integer id) {

        Optional<Author> authorOptional = authorServiceImpl.getAuthorById(id);
        Map<String, String> response = new HashMap<>();

        if (authorOptional.isPresent()) {
            authorServiceImpl.deleteAuthorById(id);
            response.put("message", "Author successfully removed");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("mesage", "The author is not registered in the database");
            return ResponseEntity.ok().body(response);
        }
    }
}
