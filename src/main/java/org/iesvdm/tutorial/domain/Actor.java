package org.iesvdm.tutorial.domain;

import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.domain.Pelicula;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String apellidos;
    private LocalDateTime ultimaActualizacion;

    @ManyToMany
    @Builder.Default
    private Set<Pelicula> peliculas = new HashSet<>();

}