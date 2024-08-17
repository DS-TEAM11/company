package org.shds.company.ai.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="CardBenefitLocations")
public class CardBenefitLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardBenefitLocationId;

    private int cardBenefitId;
    private int locationId;

}
