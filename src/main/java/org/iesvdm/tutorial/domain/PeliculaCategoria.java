package org.iesvdm.tutorial.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PeliculaCategoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Pelicula pelicula;


}
