package org.shds.company.ai.Controller;

import org.shds.company.ai.CardRecommendationRequest;
import org.shds.company.ai.Service.CardRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card-recommendation")
public class CardRecommendationController {

    @Autowired
    private CardRecommendationService cardRecommendationService;

    @PostMapping("/recommend")
    public String recommendCard(@RequestBody CardRecommendationRequest request) {
        return cardRecommendationService.recommendCard(
                request.getUserCards(),
                request.getPreviousSpending(),
                request.getPurchaseAmount(),
                request.getMerchant()
        );
    }
}
