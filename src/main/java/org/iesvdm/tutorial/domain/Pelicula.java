package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.enums.ClasificacionEnum;
import org.iesvdm.tutorial.serializer.PeliculaSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

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

    private Integer anyoLanzamiento;

    @ManyToOne
    //@JsonBackReference
    @ToString.Exclude
    private Idioma idioma;

    @ManyToOne
    @ToString.Exclude
    private Idioma idiomaOriginal;

    private Integer duracionAlquiler;

    @Column(precision = 4, scale = 2)
    private BigDecimal rentalRate;

    private Short duracion;

    @Column(precision = 5, scale = 2)
    private BigDecimal replacementCost;

    private ClasificacionEnum clasificacionEnum;

    @ManyToMany(mappedBy = "peliculas")
    @Builder.Default
    private Set<CaracteristicaEspecial> caracteristicasEspeciales = new HashSet<>();

    private LocalDateTime ultimaActualizacion;

//    @ManyToMany
//    private Set<Categoria> categorias;

    @OneToMany( mappedBy = "pelicula")
    @Builder.Default
    private Set<PeliculaCategoria> peliculaCategorias = new HashSet<>();

    @ManyToMany(mappedBy = "peliculas")
    @Builder.Default
    private Set<Actor> actores = new HashSet<>();

}
