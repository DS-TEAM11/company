package org.shds.company.ai.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="BenefitTypes")
public class BenefitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int benefitTypeId;
    private String benefitTypeName;

    // Getters and Setters
}
