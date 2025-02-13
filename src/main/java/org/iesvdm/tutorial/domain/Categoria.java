package org.iesvdm.tutorial.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 30, nullable = false)
    private String nombre;
/*
    @ManyToMany(mappedBy = "categorias")
    private Set<Pelicula> Peliculas;
*/

    @OneToMany(mappedBy = "categoria")

    private Set<PeliculaCategoria> peliculaCategorias;


}
