package org.shds.company.ai.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="CardBenefits")

public class CardBenefit {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int cardBenefitId;
    private int cardId;
    private int benefitTypeId;
    private String benefitDescription;
    private Double benefitLimit;
    private String benefitConditions;
    private String startDate;
    private String endDate;

    // Getters and Setters
}
