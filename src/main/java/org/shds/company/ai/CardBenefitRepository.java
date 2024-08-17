package org.shds.company.ai;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardBenefitRepository extends JpaRepository<CardBenefitEntity, Integer> {
    List<CardBenefitEntity> findByCardId(int cardId);
}

