package com.sgpo.monolithicSGP.routes.repository;

import com.sgpo.monolithicSGP.routes.entities.TravelRoutes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRoutesRepository extends JpaRepository<TravelRoutes, Long> {
}
