package org.shds.company.ai.Repository;

import org.shds.company.ai.Entity.CardBenefitLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardBenefitLocationRepository extends JpaRepository<CardBenefitLocation, Integer> {
    List<CardBenefitLocation> findByLocationId(int locationId);
}

