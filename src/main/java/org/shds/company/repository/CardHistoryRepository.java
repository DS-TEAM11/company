package org.shds.company.repository;

import org.shds.company.Entity.CardHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface CardHistoryRepository extends JpaRepository<CardHistory, Long> {
    @Query("SELECT SUM(ch.price) FROM CardHistory ch WHERE ch.cardNo = :cardNo AND ch.requestDate BETWEEN :startDate AND :endDate AND ch.orderState = :approval")
    Optional<Integer> getTotalSpentMonth(@Param("cardNo") String cardNo,
                                         @Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate,
                                         @Param("approval") int approval);
}