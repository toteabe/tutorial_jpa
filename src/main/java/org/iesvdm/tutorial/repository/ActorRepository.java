package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
