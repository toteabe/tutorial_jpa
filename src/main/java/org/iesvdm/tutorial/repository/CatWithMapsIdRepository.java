package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.CatWithMapsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatWithMapsIdRepository extends JpaRepository<CatWithMapsId, Long> {
}
