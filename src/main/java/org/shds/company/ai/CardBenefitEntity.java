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
public class CardBenefitEntity {
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
