package org.shds.company.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardRecommendationService {

    @Autowired
    private CardBenefitRepository cardBenefitRepository;

    @Autowired
    private CardBenefitLocationRepository cardBenefitLocationRepository;

    @Autowired
    private BenefitLocationRepository benefitLocationRepository;

    public String recommendCard(int[] userCards, int[] previousSpending, int purchaseAmount, int merchant) {
        int maxBenefit = 0;
        String bestCard = null;

        for (int i = 0; i < userCards.length; i++) {
            int cardId = userCards[i];
            int spending = previousSpending[i];

                            // Check if the card has benefits at the given merchant
                            List<CardBenefitLocationEntity> benefitLocations = cardBenefitLocationRepository.findByLocationId(merchant);
                    for (CardBenefitLocationEntity benefitLocation : benefitLocations) {
                        CardBenefitEntity cardBenefit = cardBenefitRepository.findById(benefitLocation.getCardBenefitId()).orElse(null);

                        if (cardBenefit != null && cardBenefit.getCardId() == cardId) {
                            // Calculate the benefit
                            int benefit = calculateBenefit(cardBenefit, purchaseAmount, spending);
                    if (benefit > maxBenefit) {
                        maxBenefit = benefit;
                        bestCard = String.valueOf(cardBenefit.getCardId());
                    }
                }
            }
        }

        return bestCard;
    }

    private int calculateBenefit(CardBenefitEntity cardBenefit, int purchaseAmount, int spending) {
        // Implement benefit calculation logic based on the card's benefit conditions and previous spending
        // This is a placeholder for the actual calculation logic
        return (int) (purchaseAmount * 0.05); // Example: 5% discount
    }
}
