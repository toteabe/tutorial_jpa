package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.PeliculaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeliculaCategoriaRepository extends JpaRepository<PeliculaCategoria, Long> {


    Optional<PeliculaCategoria> findPeliculaCategoriaByCategoria_IdAndPelicula_Id(Long categoriaId, Long peliculaId);

}
