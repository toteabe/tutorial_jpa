package org.iesvdm.tutorial.domain;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class PeliculaCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    //Se rompe el bucle hacia pelicula
    @ToString.Exclude
    private Pelicula pelicula;

}
