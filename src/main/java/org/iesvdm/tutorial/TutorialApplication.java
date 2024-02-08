package org.iesvdm.tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Comentario;
import org.iesvdm.tutorial.domain.Tutorial;
import org.iesvdm.tutorial.repository.ComentarioRepository;
import org.iesvdm.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class TutorialApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

    @Autowired
    TutorialRepository tutorialRepository;
    @Autowired
    ComentarioRepository comentarioRepository;
    @PersistenceContext
    EntityManager entityManager;


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
    public void grabarPadreAHijos() {

        Tutorial tutorial = new Tutorial(0,"Título 1", new HashSet<>());
        tutorialRepository.save(tutorial);

        Comentario comentario1 = new Comentario(0, "Texto1",tutorial);
        comentarioRepository.save(comentario1);
        Comentario comentario2 = new Comentario(0, "Text2", tutorial);
        comentarioRepository.save(comentario2);

        tutorialRepository.save(tutorial);

    }
    @Override
    public void run(String... args) throws Exception {

//        grabarPadreAHijos();
//        borrarHijoDePadre();
//        actualizarHijoDePadre();
//        borrarPorHijo();

//        actualizarPorHijo();

    }
}
