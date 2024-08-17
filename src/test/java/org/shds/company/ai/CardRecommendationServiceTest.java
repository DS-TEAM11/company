package org.shds.company.ai;import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.shds.company.ai.Entity.BenefitLocation;
import org.shds.company.ai.Entity.CardBenefit;
import org.shds.company.ai.Entity.CardBenefitLocation;
import org.shds.company.ai.Repository.BenefitLocationRepository;
import org.shds.company.ai.Repository.CardBenefitLocationRepository;
import org.shds.company.ai.Repository.CardBenefitRepository;
import org.shds.company.ai.Service.CardRecommendationService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CardRecommendationServiceTest {

    @Mock
    private CardBenefitRepository cardBenefitRepository;

    @Mock
    private CardBenefitLocationRepository cardBenefitLocationRepository;

    @Mock
    private BenefitLocationRepository benefitLocationRepository;

    @InjectMocks
    private CardRecommendationService cardRecommendationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecommendCard() {
        // Given
        int[] userCards = {1, 2};
        int[] previousSpending = {30000, 50000};
        int purchaseAmount = 21000;
        String merchant = "CU";

        BenefitLocation location = new BenefitLocation();
        location.setLocationId(1);
        location.setLocationName("CU");

        CardBenefit cardBenefit = new CardBenefit();
        cardBenefit.setCardBenefitId(1);
        cardBenefit.setCardId(1);
        cardBenefit.setBenefitDescription("5% discount at CU");

        CardBenefitLocation cardBenefitLocation = new CardBenefitLocation();
        cardBenefitLocation.setCardBenefitLocationId(1);
        cardBenefitLocation.setCardBenefitId(1);
        cardBenefitLocation.setLocationId(1);

        // When
        when(benefitLocationRepository.findAll()).thenReturn(List.of(location));
        when(cardBenefitLocationRepository.findByLocationId(1)).thenReturn(List.of(cardBenefitLocation));
        when(cardBenefitRepository.findById(1)).thenReturn(Optional.of(cardBenefit));

        // Act
        String recommendedCard = cardRecommendationService.recommendCard(userCards, previousSpending, purchaseAmount, merchant);

        // Then
        assertEquals("1", recommendedCard);  // 카드 1번이 추천되어야 함

        // Verify
        verify(benefitLocationRepository, times(1)).findAll();
        verify(cardBenefitLocationRepository, times(1)).findByLocationId(1);
        verify(cardBenefitRepository, times(1)).findById(1);
    }
}