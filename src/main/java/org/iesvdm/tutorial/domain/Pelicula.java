package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.serializer.PeliculaSerializer;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Pelicula.class)
@JsonSerialize(using = PeliculaSerializer.class)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String titulo;

    @ManyToOne
    //@JsonBackReference
    @ToString.Exclude
    private Idioma idioma;

    @OneToMany(mappedBy = "pelicula")
    private Set<PeliculaCategoria> peliculaCategorias;
/*
    @ManyToMany
    private Set<Categoria> categorias; //poner el mapper de categoria
*/
}
