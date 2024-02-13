package org.iesvdm.tutorial.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class CatToyUsage {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Embeddable
    public static class Pk implements Serializable {
        @ManyToOne
        @JoinColumn(name = "cat_id")
        @EqualsAndHashCode.Include
        private Cat cat;

        @ManyToOne
        @JoinColumn(name = "toy_id")
        @EqualsAndHashCode.Include
        private Toy toy;

    }

    @EmbeddedId
    @EqualsAndHashCode.Include
    private Pk id;

    private String usageStatus;
}
