package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Long> {
}
