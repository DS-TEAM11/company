package org.shds.company.approval;

import org.shds.company.DTO.PayDTO;
import org.shds.company.Entity.CardHistory;

import java.util.Map;

public interface ApprovalService {
    void saveCardHistory(PayDTO payDTO, int approval);
    int checkCardValidation(PayDTO payDTO);
}
