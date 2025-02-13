package org.iesvdm.tutorial.Ejercicio4_2;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.domain.PeliculaCategoria;
import org.iesvdm.tutorial.serializer.PeliculaSerializer;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = org.iesvdm.tutorial.domain.Pelicula.class)
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
    private Idioma idiomaOriginal;

    @Column(precision = 4, scale = 2)
    private BigDecimal rentalRate;

    private short duracion;

    @Column(precision = 5, scale = 2)
    private BigDecimal replacementCost;

    private ClasificacionEnum clasificacionEnum;

    @ManyToMany(mappedBy = "peliculas")
    private Set<CaracterisiticasEspecial> caracteristicasEspeciales;

    @OneToMany(mappedBy = "pelicula")
    private Set<PeliculaCategoria> peliculaCategoria;

    @ManyToMany(mappedBy = "peliculas")
    private Set<Actor> actores;


}
