package org.iesvdm.tutorial.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 9, unique = true)
    private String nif;

    @Column(length = 30)
    private String nombre;

    @Column(length = 120)
    private String apellidos;

    @OneToOne(mappedBy = "socio")
    private Tarjeta tarjeta;

}
