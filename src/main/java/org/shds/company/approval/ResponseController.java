package org.shds.company.approval;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shds.company.DTO.PayDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ResponseController {
    private final ApprovalService approvalService;

    @PostMapping("/api/payment")
    public CompletableFuture<Integer> receivePayment(@RequestBody PayDTO payDTO) {

        //유효성 검사 처리
        int approval = approvalService.checkCardValidation(payDTO);
        //카드사 결제로그 저장
        approvalService.saveCardHistory(payDTO, approval);
        // 0: 승인 1: 정보 불일치 2: 유효기간 만료 3: 한도초과
        return CompletableFuture.completedFuture(approval);
    }
}
