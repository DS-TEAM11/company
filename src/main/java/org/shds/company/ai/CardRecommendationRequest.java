package org.shds.company.ai;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CardRecommendationRequest {
    private int[] userCards;
    private int[] previousSpending;
    private int purchaseAmount;
    private String merchant;

    // Getters and Setters
}