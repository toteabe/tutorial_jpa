package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.serializer.PeliculaSerializer;

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
        property = "id", scope = Idioma.class)
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String nombre;

    @OneToMany(mappedBy = "idioma", fetch = FetchType.EAGER)
    @Builder.Default
    //@JsonIgnore
    //@JsonManagedReference
    private Set<Pelicula> peliculas = new HashSet<>();


    @OneToMany(mappedBy = "idiomaOriginal")
    @Builder.Default
    //@JsonIgnore
    //@JsonManagedReference
    private Set<Pelicula> peliculasIdiomaOriginal = new HashSet<>();
}
