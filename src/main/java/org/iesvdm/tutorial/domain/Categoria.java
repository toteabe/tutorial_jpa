package org.iesvdm.tutorial.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 30, nullable = false)
    private String nombre;
//
//    @ManyToMany( mappedBy = "categorias")
//    private Set<Pelicula> peliculas;

    @OneToMany(mappedBy = "categoria")
    //Se rompe el bucle hacia PeliculaCategoria, que a su vez no continua hacia Pelicula
    @ToString.Exclude
    private Set<PeliculaCategoria> peliculasCategorias;

}
