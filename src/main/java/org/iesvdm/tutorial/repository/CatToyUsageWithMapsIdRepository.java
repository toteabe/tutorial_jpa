package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.CatToyUsage;
import org.iesvdm.tutorial.domain.CatToyUsageWithMapsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatToyUsageWithMapsIdRepository extends JpaRepository<CatToyUsageWithMapsId, CatToyUsageWithMapsId.Pk> {
}
