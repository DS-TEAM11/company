package org.shds.company.ai.Repository;
import org.shds.company.ai.Entity.BenefitLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitLocationRepository extends JpaRepository<BenefitLocation, Integer> {
    List<Integer> findByLocationNameContains(String name);
}
