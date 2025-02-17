package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.ToyWithMapsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyWithMapsIdRepository extends JpaRepository<ToyWithMapsId, Long> {
}
