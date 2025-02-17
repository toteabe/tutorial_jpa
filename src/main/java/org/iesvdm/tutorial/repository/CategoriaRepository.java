package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
