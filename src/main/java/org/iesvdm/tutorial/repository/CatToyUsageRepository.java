package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.CatToyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatToyUsageRepository extends JpaRepository<CatToyUsage, CatToyUsage.Pk> {
}
