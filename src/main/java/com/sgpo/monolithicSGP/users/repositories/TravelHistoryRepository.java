package com.sgpo.monolithicSGP.users.repositories;

import com.sgpo.monolithicSGP.users.entities.TravelHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TravelHistoryRepository extends JpaRepository<TravelHistory, Long> {
    List<TravelHistory> findByUserId(UUID userId);
    boolean existsByUserIdAndTravelRouteIdAndTravelDate(UUID userId, Long travelRouteId, String travelDate);

}
