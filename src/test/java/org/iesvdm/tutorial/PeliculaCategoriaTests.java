package org.iesvdm.tutorial;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Categoria;
import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.domain.PeliculaCategoria;
import org.iesvdm.tutorial.enums.ClasificacionEnum;
import org.iesvdm.tutorial.repository.CategoriaRepository;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.iesvdm.tutorial.repository.PeliculaCategoriaRepository;
import org.iesvdm.tutorial.repository.PeliculaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TEST 2 ONETOMANY CON CLAVES FORANEAS NO IDENTIFICATIVAS, ES DECIR MANY CON CLAVE SINTETICA
 * SUBROGADA
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@SpringBootTest
public class PeliculaCategoriaTests {

    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    PeliculaCategoriaRepository peliculaCategoriaRepository;
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
    void crearPeliculaCategoriaTest() {

        Categoria categoria = Categoria.builder()
                .nombre("Accion")
                .build();
        this.categoriaRepository.save(categoria);

        Idioma idiomaFr = Idioma.builder()
                .nombre("Francés")
                .peliculas(new HashSet())
                .build();
        idiomaFr = idiomaRepository.save(idiomaFr);

        Pelicula pelicula = Pelicula.builder()
                .titulo("Pelicula 1")
                .anyoLanzamiento(2020)
                .duracionAlquiler(3)
                .rentalRate(BigDecimal.valueOf(20.56D))
                .idioma(idiomaFr)
                .clasificacionEnum(ClasificacionEnum.JUVENIL)
                .build();

        idiomaFr.getPeliculas().add(pelicula);
        peliculaRepository.save(pelicula);

        PeliculaCategoria peliculaCategoria = new PeliculaCategoria(null,
                                            categoria, pelicula);
        peliculaCategoriaRepository.save(peliculaCategoria);

    }

    @Order(2)
    @Test
//No puedes utilizar la anotación @Transactional para marcar
//el metodo de un test como transactional
//    @Transactional
    void leerPeliculaYCategoriaPorCategoria() {

        //Para tener acceso a los lazy en los tests necesitas wrapear los accesos
        //lazy mediante una transaccion, en el tiempo de vida de una transaccion
        //la sesión de jpa está viva y pueden resolverse colecciones lazy

        //Si comentas el wrap de transaction dara una LazyInitializationException
        transactionTemplate.executeWithoutResult(transactionStatus -> {

            List<Categoria> listCategoria = this.categoriaRepository
                    .findAll();

            listCategoria.forEach(categoria -> {

                Set<PeliculaCategoria> setPC = categoria
                        .getPeliculasCategorias();

                setPC.forEach(peliculaCategoria -> {
                    Pelicula pelicula = peliculaCategoria.getPelicula();

                    System.out.println(pelicula);

                });

            });

        });//END-TX
    }

    @Order(3)
    @Test
    void desasociarCategoria() {

        //Quiero desasociar la categoria 1 de la pelicula 1

        //Busco peliculacategoria para categoria 1 y pelicula 1
        PeliculaCategoria peliculaCategoria = peliculaCategoriaRepository
                .findPeliculaCategoriaByCategoria_IdAndPelicula_Id(1L, 1L)
                .orElse(null);

        peliculaCategoriaRepository.delete(peliculaCategoria);


    }

}
