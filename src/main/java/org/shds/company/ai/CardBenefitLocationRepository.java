package org.shds.company.ai;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardBenefitLocationRepository extends JpaRepository<CardBenefitLocationEntity, Integer> {
    List<CardBenefitLocationEntity> findByLocationId(int locationId);
}

