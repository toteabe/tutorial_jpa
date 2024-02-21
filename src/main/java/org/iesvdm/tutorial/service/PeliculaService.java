package org.iesvdm.tutorial.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.exception.EntityNotFoundException;
import org.iesvdm.tutorial.exception.PeliculaNotFoundException;
import org.iesvdm.tutorial.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepository;

    @PersistenceContext
    EntityManager entityManager;


    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }


    @Transactional
    public Pelicula save(Pelicula pelicula) {

        this.peliculaRepository.save(pelicula);

        this.entityManager.refresh(pelicula);

        return pelicula;
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getId())  ?
                        this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

}
