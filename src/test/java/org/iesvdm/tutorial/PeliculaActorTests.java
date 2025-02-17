package org.iesvdm.tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Actor;
import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.enums.ClasificacionEnum;
import org.iesvdm.tutorial.repository.ActorRepository;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.iesvdm.tutorial.repository.PeliculaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * MANYTOMANY SIN CASCADE {PERSIST, MERGE} POR LADO PROPIETARIO (LADO COM MAPPEDBY)
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@SpringBootTest
public class PeliculaActorTests {


    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    IdiomaRepository idiomaRepository;

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    @BeforeEach
    public void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Order(1)
    @Test
    void crearPeliculaActorTest() {

        Actor actor = Actor.builder()
                .nombre("Bruce")
                .apellidos("Lee")
                //No hace falta al utilizar inicializacion con
                //@Builder.Default en actores
                //.peliculas(new HashSet<>())
                .build();

        actorRepository.save(actor);

        Idioma idiomaCh = Idioma.builder()
                .nombre("Inglés")
                //No utilizo @Builder.Default luego tengo
                //que inicializar con el builder
                .peliculas(new HashSet())
                .build();

        idiomaCh = idiomaRepository.save(idiomaCh);

        Pelicula pelicula = Pelicula.builder()
                .titulo("Enter the Dragon")
                .anyoLanzamiento(1973)
                .duracionAlquiler(3)
                .rentalRate(BigDecimal.valueOf(20.56D))
                .idioma(idiomaCh)
                //No hace falta al utilizar inicializacion con
                //@Builder.Default en actores
                //.actores(new HashSet<>())
                .clasificacionEnum(ClasificacionEnum.JUVENIL)
                .build();

        peliculaRepository.save(pelicula);

        //Asociar actor-pelicula

        //Relacion bidireccional establezco ambos extremos
        pelicula.getActores().add(actor);
        actor.getPeliculas().add(pelicula);

        peliculaRepository.save(pelicula);
        //ES NECESARIO TAMBIEN ACTUALIZAR actor AL NO HABER CASCADE EN pelicula LADO PROPIETARIO
        actorRepository.save(actor);
    }

    @Order(1)
    @Test
    void desasociarPeliculaActorTest() {
        //Para resolver las colecciones lazies en los tests necesito una transaccion
        transactionTemplate.executeWithoutResult(transactionStatus -> {

            Pelicula pelicula = peliculaRepository.findById(1L).orElse(null);
            Actor actor = actorRepository.findById(1L).orElse(null);

            //SE ELIMINA LA ASOCIACION EN AMBOS AL SER BIDIRECCIONAL
            pelicula.getActores().remove(actor);
            actor.getPeliculas().remove(pelicula);

            peliculaRepository.save(pelicula);
            actorRepository.save(actor);

        });

    }
}
