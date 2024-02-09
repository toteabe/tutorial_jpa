package org.iesvdm.tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Comentario;
import org.iesvdm.tutorial.domain.Tutorial;
import org.iesvdm.tutorial.repository.ComentarioRepository;
import org.iesvdm.tutorial.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

@SpringBootTest
class TutorialApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;
    @Autowired
    ComentarioRepository comentarioRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void actualizarPorHijo() {

        Tutorial tutorial = tutorialRepository.findById(1L).orElse(null);

        //Si se utliza un fetch LAZY, mejor estrategia realizar un join fetch en JPQL
        //y cargar en la colección. NOTA: si utilizas EAGER puedes prescindir de join fetch.
        List<Comentario> comentarios = entityManager.createQuery(
                        "select c " +
                                "from Comentario c " +
                                "join fetch c.tutorial " +
                                "where c.tutorial.id = :id", Comentario.class)
                .setParameter("id", tutorial.getId())
                .getResultList();
        tutorial.setComentarios(new HashSet<>(comentarios));
        //

        tutorial.getComentarios().forEach(System.out::println);

        Comentario comentarioAActualizar = tutorial.getComentarios().stream().findFirst().orElse(null);
        System.out.println("Comentario a ACTUALIZAR: " + comentarioAActualizar);

        comentarioAActualizar.setTexto("EH!!!!!!");

        comentarioRepository.save(comentarioAActualizar);


    }

    @Test
    public void actualizarHijoDePadre() {

        Tutorial tutorial = tutorialRepository.findById(1L).orElse(null);

        //Si se utliza un fetch LAZY, mejor estrategia realizar un join fetch en JPQL
        //y cargar en la colección. NOTA: si utilizas EAGER puedes prescindir de join fetch.
        List<Comentario> comentarios = entityManager.createQuery(
                        "select c " +
                                "from Comentario c " +
                                "join fetch c.tutorial " +
                                "where c.tutorial.id = :id", Comentario.class)
                .setParameter("id", tutorial.getId())
                .getResultList();
        tutorial.setComentarios(new HashSet<>(comentarios));
        //

        tutorial.getComentarios().forEach(System.out::println);

        Comentario comentarioAActualizar = tutorial.getComentarios().stream().findFirst().orElse(null);
        System.out.println("Comentario a ACTUALIZAR: " + comentarioAActualizar);

        comentarioAActualizar.setTexto("EH!!!!!!");

        tutorialRepository.save(tutorial);

    }

    @Test
    public void borrarPorTodoPadre() {

        Tutorial tutorial = tutorialRepository.findById(1L).orElse(null);
        tutorialRepository.delete(tutorial);

    }

    @Test
    public void borrarPorHijo() {

        Tutorial tutorial = tutorialRepository.findById(1L).orElse(null);

        //Si se utliza un fetch LAZY, mejor estrategia realizar un join fetch en JPQL
        //y cargar en la colección. NOTA: si utilizas EAGER puedes prescindir de join fetch.
        List<Comentario> comentarios = entityManager.createQuery(
                        "select c " +
                                "from Comentario c " +
                                "join fetch c.tutorial " +
                                "where c.tutorial.id = :id", Comentario.class)
                .setParameter("id", tutorial.getId())
                .getResultList();
        tutorial.setComentarios(new HashSet<>(comentarios));
        //

        tutorial.getComentarios().forEach(System.out::println);

        Comentario comentarioABorrar = tutorial.getComentarios().stream().findFirst().orElse(null);
        System.out.println("Comentario a BORRAR: " + comentarioABorrar);

        comentarioRepository.delete(comentarioABorrar);

    }

    @Test
    public void borrarHijoDePadre() {

        Tutorial tutorial = tutorialRepository.findById(1L).orElse(null);

        //Si se utlizas un fetch LAZY, mejor estrategia realizar un join fetch en JPQL
        //y cargar en la colección. NOTA: si utilizas EAGER puedes prescindir de join fetch.
        List<Comentario> comentarios = entityManager.createQuery(
                        "select c " +
                                "from Comentario c " +
                                "join fetch c.tutorial " +
                                "where c.tutorial.id = :id", Comentario.class)
                .setParameter("id", tutorial.getId())
                .getResultList();
        tutorial.setComentarios(new HashSet<>(comentarios));
        //

        tutorial.getComentarios().forEach(System.out::println);

        Comentario comentarioABorrar = tutorial.getComentarios().stream().findFirst().orElse(null);
        System.out.println("Comentario a BORRAR: " + comentarioABorrar);

        //borrar por orphanremoval
        comentarioABorrar.setTutorial(null);
        tutorial.getComentarios().remove(comentarioABorrar);
        tutorialRepository.save(tutorial);
        //
    }

    @Test
    public void grabarPadreAHijosSinColeccion() {

        Tutorial tutorial = new Tutorial(0,"Título 1", new HashSet<>());
        tutorialRepository.save(tutorial);

        Comentario comentario1 = new Comentario(0, "Texto1",tutorial);
        tutorial.getComentarios().add(comentario1);
        comentarioRepository.save(comentario1);
        Comentario comentario2 = new Comentario(0, "Text2", tutorial);
        comentarioRepository.save(comentario2);

        tutorialRepository.save(tutorial);

    }

    @Test
    public void grabarPadreAHijosPorColeccion() {

        Tutorial tutorial = new Tutorial(0,"Título 1", new HashSet<>());

        Comentario comentario1 = new Comentario(0, "Texto1",tutorial);
        tutorial.getComentarios().add(comentario1);
        //NO PUEDO AÑADIR MÁS COMENTARIOS SIN SALVAR ANTES TUTORIAL
        //DADO QUE LA COLECCIÓN ES UN SET Y NO PUEDO TENER 2 COMENTARIOS
        // CON ID = 0 EN LA COLECCIÓN SET DE ARRANQUE, SI CAMBIO A
        //COLECCIÓN ARRAYLIST SÍ PODRÍA

        tutorialRepository.save(tutorial);

        Comentario comentario2 = new Comentario(0, "Texto2",tutorial);
        tutorial.getComentarios().add(comentario2);

        tutorialRepository.save(tutorial);

    }

}
