package org.iesvdm.tutorial;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Cat;
import org.iesvdm.tutorial.domain.CatToyUsage;
import org.iesvdm.tutorial.domain.Toy;
import org.iesvdm.tutorial.repository.CatRepository;
import org.iesvdm.tutorial.repository.CatToyUsageRepository;
import org.iesvdm.tutorial.repository.ToyRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashSet;

/**
 * TEST 2 ONETOMANY CON CLAVES ONE IDENTIFICATIVAS COMO CLAVE MULTIPLE DE MANY
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@SpringBootTest
public class CatToyUsageTests {

    @Autowired
    CatRepository catRepository;

    @Autowired
    ToyRepository toyRepository;

    @Autowired
    CatToyUsageRepository catToyUsageRepository;

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

        Cat catMessi = new Cat(null, "messi", new HashSet<>());
        catRepository.save(catMessi);

        Cat catRonaldo = new Cat(null, "ronaldo", new HashSet<>());
        catRepository.save(catRonaldo);

        Toy toyPelota = new Toy(null, "pelota", new HashSet<>());
        toyRepository.save(toyPelota);

        Toy toyMuneco = new Toy(null, "muñeco", new HashSet<>());
        toyRepository.save(toyMuneco);

        CatToyUsage catToyUsage1 = new CatToyUsage(new CatToyUsage.Pk(catMessi, toyPelota), "usado");
        catMessi.getCatToyUsages().add(catToyUsage1);
        toyPelota.getCatToyUsages().add(catToyUsage1);

        catToyUsageRepository.save(catToyUsage1);

        CatToyUsage catToyUsage2 = new CatToyUsage(new CatToyUsage.Pk(catRonaldo, toyMuneco), "más usado");
        catRonaldo.getCatToyUsages().add(catToyUsage2);
        toyMuneco.getCatToyUsages().add(catToyUsage2);

        catToyUsageRepository.save(catToyUsage2);

    }

}
