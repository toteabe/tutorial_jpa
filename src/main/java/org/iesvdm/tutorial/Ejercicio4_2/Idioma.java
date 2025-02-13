package org.iesvdm.tutorial.Ejercicio4_2;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.tutorial.domain.Pelicula;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = org.iesvdm.tutorial.domain.Idioma.class)

public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String nombre;

    @OneToMany(mappedBy = "idioma", fetch = FetchType.EAGER)
    //@JsonIgnore
    //@JsonManagedReference
    private Set<Pelicula> setPeliculas = new HashSet<>();


    @OneToMany(mappedBy = "idiomaOriginal", fetch = FetchType.EAGER)
    //@JsonIgnore
    //@JsonManagedReference
    private Set<Pelicula> setPeliculaOriginal = new HashSet<>();


}
