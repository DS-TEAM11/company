package org.shds.company.ai.Entity;

import lombok.Data;

@Data
public class CustomerInfo {
    String[] ownedCardList; //소유한 카드 리스트
    int[] ownedCardCodeList; //소유한 카드 코드 매칭
    int[] previousSpending; //각 카드별 전월 실적
    int[] usedBenefit; //각 카드별 받았던 혜택 금액(이번달만)
    String merchant; //구매하고자 하는 업장명
    int price; //구매하고자 하는 상품 가격
}
