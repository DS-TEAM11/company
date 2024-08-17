package org.shds.company.ai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class BenefitTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int benefitTypeId;
    private String benefitTypeName;

    // Getters and Setters
}
