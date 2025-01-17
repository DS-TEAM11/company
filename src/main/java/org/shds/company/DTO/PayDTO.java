package org.shds.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayDTO {
    private String product;
    private Integer price;
    private String cardNo;
    private String payDate;
    private String franchiseName;
    private String franchiseCode;
    private String requestName;
}
