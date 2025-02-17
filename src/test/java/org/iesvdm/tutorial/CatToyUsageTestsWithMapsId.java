package org.iesvdm.tutorial;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.*;
import org.iesvdm.tutorial.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashSet;

/**
 * TEST 2 ONETOMANY CON CLAVES ONE IDENTIFICATIVAS COMO CLAVE MULTIPLE DE MANY
 * USANDO MAPSID
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@SpringBootTest
public class CatToyUsageTestsWithMapsId {

    @Autowired
    CatWithMapsIdRepository catRepository;

    @Autowired
    ToyWithMapsIdRepository toyRepository;

    @Autowired
    CatToyUsageWithMapsIdRepository catToyUsageRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    @BeforeEach
    public void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Test
    @Order(1)
    void grabar2OneToMany() {

        CatWithMapsId catMessi = new CatWithMapsId(null, "messi", new HashSet<>());
        catRepository.save(catMessi);

        CatWithMapsId catRonaldo = new CatWithMapsId(null, "ronaldo", new HashSet<>());
        catRepository.save(catRonaldo);

        ToyWithMapsId toyPelota = new ToyWithMapsId(null, "pelota", new HashSet<>());
        toyRepository.save(toyPelota);

        ToyWithMapsId toyMuneco = new ToyWithMapsId(null, "muñeco", new HashSet<>());
        toyRepository.save(toyMuneco);

        CatToyUsageWithMapsId catToyUsage1 = new CatToyUsageWithMapsId(new CatToyUsageWithMapsId.Pk(catMessi.getId(), toyPelota.getId()),
                                                                        catMessi,
                                                                        toyPelota,
                                                            "usado");
        catMessi.getCatToyUsages().add(catToyUsage1);
        toyPelota.getCatToyUsages().add(catToyUsage1);

        catToyUsageRepository.save(catToyUsage1);

        CatToyUsageWithMapsId catToyUsage2 = new CatToyUsageWithMapsId(new CatToyUsageWithMapsId.Pk(catRonaldo.getId(), toyMuneco.getId()),
                                                                        catRonaldo,
                                                                        toyMuneco,
                                                            "más usado");
        catRonaldo.getCatToyUsages().add(catToyUsage2);
        toyMuneco.getCatToyUsages().add(catToyUsage2);

        catToyUsageRepository.save(catToyUsage2);

    }

}
