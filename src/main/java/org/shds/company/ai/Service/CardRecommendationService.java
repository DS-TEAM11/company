package org.shds.company.ai.Service;

import org.shds.company.ai.Entity.BenefitLocation;
import org.shds.company.ai.Entity.CardBenefit;
import org.shds.company.ai.Entity.CardBenefitLocation;
import org.shds.company.ai.Repository.BenefitLocationRepository;
import org.shds.company.ai.Repository.CardBenefitLocationRepository;
import org.shds.company.ai.Repository.CardBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardRecommendationService {

    @Autowired
    private CardBenefitRepository cardBenefitRepository;

    @Autowired
    private CardBenefitLocationRepository cardBenefitLocationRepository;

    @Autowired
    private BenefitLocationRepository benefitLocationRepository;

    public String recommendCard(int[] userCards, int[] previousSpending, int purchaseAmount, String merchant) {
        int maxBenefit = 0;
        String bestCard = null;

        // merchant를 사용해 location_name과 매칭되는 location_id를 찾음
        Optional<BenefitLocation> locationOpt = benefitLocationRepository.findAll().stream()
                .filter(location -> location.getLocationName().equalsIgnoreCase(merchant))
                .findFirst();

        if (locationOpt.isEmpty()) {
            return "No matching merchant found";
        }

        BenefitLocation location = locationOpt.get();
        int locationId = location.getLocationId();

        // 사용자의 카드에 대해 반복
        for (int i = 0; i < userCards.length; i++) {
            int cardId = userCards[i];
            int spending = previousSpending[i];

            // 해당 위치에서 사용할 수 있는 혜택을 가져옴
            List<CardBenefitLocation> benefitLocations = cardBenefitLocationRepository.findByLocationId(locationId);
            for (CardBenefitLocation benefitLocation : benefitLocations) {
                CardBenefit cardBenefit = cardBenefitRepository.findById(benefitLocation.getCardBenefitId()).orElse(null);

                if (cardBenefit != null && cardBenefit.getCardId() == cardId) {
                    // 혜택을 계산
                    int benefit = calculateBenefit(cardBenefit, purchaseAmount, spending);
                    if (benefit > maxBenefit) {
                        maxBenefit = benefit;
                        bestCard = String.valueOf(cardBenefit.getCardId());
                    }
                }
            }
        }

        return bestCard == null ? "No suitable card found" : bestCard;
    }

    private int calculateBenefit(CardBenefit cardBenefit, int purchaseAmount, int spending) {
        // 실제 비즈니스 로직에 따라 혜택을 계산
        // 예: 5% 할인
        return (int) (purchaseAmount * 0.05);
    }
}