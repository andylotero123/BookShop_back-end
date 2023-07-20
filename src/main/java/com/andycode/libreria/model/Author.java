package com.andycode.libreria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

//@JsonIdentityInfo para que Author me muestre a libros, pero anidados, lo que no es correcto
@Entity //esto es una entidad
@Table(name = "author")//para mapear o relacionar esta clase con la taba author
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Author {

    //id 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//cuando es autoincrementable
    private int id;
    @NotNull//que el dato no sea nulo
    @NotBlank //para validar que no este vacio, o comillas vacias
    private String name;

    @Column(name = "lastname") //para ubicar la columna lastname en la tabla author
    private String lastname;
    private String nationality;

    //@OneToMany(mappedBy = "author") //para establecer una relacion de uno a muchos (un autor puede tener varios libros), no elimina datos existentes , recupera y actualizza solo lo que se envia
    //@JoinColumn(name = "id_author", referencedColumnName = "id") //establezco la asociacion de las dos columnas, referencedColumnName = "id", este id es del author      
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER) //para establecer una relacion de uno a muchos (un autor puede tener varios libros), no elimina datos existentes , recupera y actualizza solo lo que se envia
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//devuelve en formato Json, por lo que author tiene multiples libros 
    @JsonBackReference //para refernciar quien es el propietario, una lista en formato JSon
    private List<Book> books = new ArrayList<Book>();//se crea un objeto de tipo lista
}
