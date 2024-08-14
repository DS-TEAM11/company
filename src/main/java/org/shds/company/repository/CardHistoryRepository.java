package org.shds.company.repository;

import org.shds.company.Entity.CardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHistoryRepository extends JpaRepository<CardHistory, Long> {
}
