package org.shds.company.ai;

import lombok.Data;

@Data
public class ChatDTO {
    String[] userCards;
    int[] previousSpending;
    int purchaseAmount;
    String merchant;
    String message = "User cards: " + userCards +
            "\nPrevious month spending: " + previousSpending +
            "\nCurrent purchase: " + purchaseAmount + "at" + merchant +
            "\nRelevant card info: " + cardInfo +
            "\nBased on this information, which card would you recommend for this purchase? Provide your recommendation in the structured format specified.";
}
