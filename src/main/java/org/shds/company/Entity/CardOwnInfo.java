package org.shds.company.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "card_own_info")
public class CardOwnInfo {

    @Id
    private String cardNo;
    private String validPeriod;
    private Integer cardLimit;
    private String cardOwner;
    private Integer cardState;
    private Integer Key;

}
