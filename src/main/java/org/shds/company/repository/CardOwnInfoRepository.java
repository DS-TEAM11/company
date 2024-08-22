package org.shds.company.repository;

import org.shds.company.Entity.CardOwnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardOwnInfoRepository extends JpaRepository<CardOwnInfo, String> {
    Optional<Object> findByCardNo(String cardNo);
}
