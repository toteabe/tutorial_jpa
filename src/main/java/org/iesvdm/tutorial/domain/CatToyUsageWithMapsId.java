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
public class CatToyUsageWithMapsId {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Embeddable
    public static class Pk implements Serializable {

        private Long catId;

        private Long toyId;

    }

    @EmbeddedId
    @EqualsAndHashCode.Include
    private Pk id;

    @ManyToOne
    @MapsId("catId")
    @JoinColumn(name = "cat_id")
    @EqualsAndHashCode.Include
    private CatWithMapsId cat;

    @ManyToOne
    @MapsId("toyId")
    @JoinColumn(name = "toy_id")
    @EqualsAndHashCode.Include
    private ToyWithMapsId toy;

    private String usageStatus;
}
