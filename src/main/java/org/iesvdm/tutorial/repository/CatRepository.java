package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
