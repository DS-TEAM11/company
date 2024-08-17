package org.shds.company.ai.Repository;

import org.shds.company.ai.Entity.CardBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardBenefitRepository extends JpaRepository<CardBenefit, Integer> {
    List<CardBenefit> findByCardId(int cardId);
}

