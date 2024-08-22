package org.shds.company.repository;

import org.junit.jupiter.api.Test;
import org.shds.company.Entity.CardHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardHistroyRepositoryTests {

    @Autowired
    private CardHistoryRepository cardHistoryRepository;

    @Test
    public void insertCardHistory() {
        CardHistory cardHistory = CardHistory.builder()
                .requestName("test3")
                .orderState(1)
                .cardNo("1111-1111-1111-1111")
                .build();
        System.out.println(cardHistoryRepository.save(cardHistory));
    }
}