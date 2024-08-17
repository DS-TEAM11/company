package org.shds.company.ai.Repository;

import org.shds.company.ai.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
