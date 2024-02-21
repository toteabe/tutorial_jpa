package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public List<Pelicula> findByTituloContainingIgnoreCaseOrderByTituloAsc(String titulo);

    public List<Pelicula> findByTituloContainingIgnoreCaseOrderByTituloDesc(String titulo);

}
