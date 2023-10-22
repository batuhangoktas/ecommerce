package com.accounting.ecommerce.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 332223345;

    private String firstName;
    private String lastName;
    private String email;
    private Double amount;
    private String productName;
    private String billNo;
    private boolean approvalStatus;

}
