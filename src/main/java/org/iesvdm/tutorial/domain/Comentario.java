package org.iesvdm.tutorial.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String texto;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "tutorial_id",updatable = false)
    @ToString.Exclude
    private Tutorial tutorial;

}
