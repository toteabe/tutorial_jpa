package org.iesvdm.tutorial.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(unique = true, length = 9)
    private  String nif;

    @Column(length = 30)
    private String nombre;
    @Column(length = 120)
    private String apellido;

    @OneToOne(mappedBy = "socio")
    private Tarjeta tarjeta;

}
