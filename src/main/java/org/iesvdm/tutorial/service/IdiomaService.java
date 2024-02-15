package org.iesvdm.tutorial.service;


import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.exception.EntityNotFoundException;
import org.iesvdm.tutorial.exception.NotCouplingIdException;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    public List<Idioma> all() {
        return this.idiomaRepository.findAll();
    }

    public Idioma save(Idioma idioma) {
        return this.idiomaRepository.save(idioma);
    }

    public Idioma one(Long id) {
        return this.idiomaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Idioma.class));
    }

    public Idioma replace(Long id, Idioma idioma) {

        return this.idiomaRepository.findById(id).map( i -> {
                    if (id.equals(idioma.getId())) return this.idiomaRepository.save(idioma);
                    else throw new NotCouplingIdException(id, idioma.getId(), Idioma.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Idioma.class));

    }

    public void delete(Long id) {
        this.idiomaRepository.findById(id).map(t -> {this.idiomaRepository.delete(t);
                    return t;})
                .orElseThrow(() -> new EntityNotFoundException(id, Idioma.class));
    }
}
