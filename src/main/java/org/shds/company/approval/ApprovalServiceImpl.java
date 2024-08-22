package org.shds.company.approval;

import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shds.company.DTO.PayDTO;
import org.shds.company.Entity.CardHistory;
import org.shds.company.Entity.CardOwnInfo;
import org.shds.company.repository.CardHistoryRepository;
import org.shds.company.repository.CardOwnInfoRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService{
    private final CardHistoryRepository cardHistoryRepository;
    private final CardOwnInfoRepository cardOwnInfoRepository;

    @Override
    public void saveCardHistory(PayDTO payDTO, int approval) {

        //결제 유효성 확인 로직 추가해야 함 -> 완료
        CardHistory cardHistory = CardHistory.builder()
                .requestName(payDTO.getRequestName())
                .orderState(approval)
                .cardNo(payDTO.getCardNo())
                .requestName(payDTO.getRequestName())
                .price(payDTO.getPrice())
                .franchiseName(payDTO.getFranchiseName())
                .franchiseCode(payDTO.getFranchiseCode())
                .build();
        cardHistoryRepository.save(cardHistory);
    }

    //카드 유효성 검사 로직
    @Override
    public int checkCardValidation(PayDTO payDTO) {
        //TODO:서버 오류 났을 경우 추가해야 함
        
        // 정보 가져오기
        String cardNo = payDTO.getCardNo();
        String requestName = payDTO.getRequestName();
        Integer price = payDTO.getPrice();
        String payDate = payDTO.getPayDate();

        // 1. 카드 소유자 검증
        CardOwnInfo cardOwnInfo = (CardOwnInfo) cardOwnInfoRepository.findByCardNo(cardNo).orElse(null);
        log.info(cardOwnInfo);

        if (cardOwnInfo == null) {
            return 1; // 카드 정보가 없음
        }
        if (!cardOwnInfo.getCardOwner().equals(requestName)) {
            return 1; // 이름 불일치
        }

        // 결제 날짜를 LocalDateTime으로 변환
        DateTimeFormatter payDateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate paymentDate = LocalDate.parse(payDate, payDateFormatter);
        LocalDateTime paymentDateTime = paymentDate.atStartOfDay();  // 시간을 00:00:00으로 설정

        DateTimeFormatter validPeriodFormatter = DateTimeFormatter.ofPattern("MM/yy");

        // mm/yy 문자열 파싱
        YearMonth yearMonth = YearMonth.parse(cardOwnInfo.getValidPeriod(), validPeriodFormatter);

        // 유효기간의 마지막 날을 LocalDateTime으로 변환
        LocalDate validDate = yearMonth.atEndOfMonth();
        LocalDateTime validDateTime = validDate.atTime(23, 59, 59); // LocalDate -> LocalDateTime으로

        if (paymentDateTime.isAfter(validDateTime)) {
            return 2; // 유효기간 만료
        }

        // 3. 한도 검증
        LocalDateTime firstDayOfMonth = paymentDate.withDayOfMonth(1).atStartOfDay();
        LocalDateTime lastDayOfMonth = paymentDate.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);

        // 총 결제 금액 조회
        Optional<Integer> totalSpentThisMonthOptional = cardHistoryRepository.getTotalSpentMonth(cardNo, firstDayOfMonth, lastDayOfMonth, 0);
        int totalSpentThisMonth = totalSpentThisMonthOptional.orElse(0); // 값이 없으면 0으로 설정

        int cardLimit = cardOwnInfo.getCardLimit();

        // 현재 결제 금액과 합산하여 한도 초과 여부 확인
        if (totalSpentThisMonth + price > cardLimit) {
            return 3; // 한도 초과
        }
        return 0; // 결제 승인
    }


}
