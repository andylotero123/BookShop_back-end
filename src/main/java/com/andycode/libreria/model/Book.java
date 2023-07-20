
package com.andycode.libreria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

//@JsonPropertyInfo, para que libros muestre author
//NOta: a Book lo dejamos como la clase propiestaria, ya que a esta llega muchos libros, un author puede tener 1 o muchos libros
@Entity //para establecer esta clase como entidad
@Table(name = "book") //defino la tabla con la cual voy a trabjar
@Data //para metodos accesores y constructores
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {
    
    @Id //eatablezco el este id como lave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//porque se definió el Id como autonumerico
    private int id;
    
    private String title;
    private String description;
    //private int id_author;
    private String category;
    private Double price;
    private String isbn;
    private int pages;
    private LocalDate publication_date;
    private String image;
    
    //NOTA: ambos deberan coincidir, pero si en la tabla tengo un numbre diferente, hago una referencia aqui, sin importar el nombre del atributo. @Column(name = "nombre_exacto_del_campo_de_tabla")
    //@Column(name = "publication_date")
    //private LocalDate publica_date;
    
    @ManyToOne(fetch = FetchType.EAGER) //establezco la relación, varios libros pueden tener un author
    @JoinColumn(name = "id_author")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//devuelve el objeto Author en formato Json, por lo que author tiene multiples libros 
    //@JsonBackReference //Retorna un objeto en formato JSon
    private Author author; //este atributo va en Posman para relacionar el autor con el libro
}
