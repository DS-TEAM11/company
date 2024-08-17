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
public class CardBenefitLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardBenefitLocationId;

    private int cardBenefitId;
    private int locationId;

}