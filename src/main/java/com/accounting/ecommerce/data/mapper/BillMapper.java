package com.accounting.ecommerce.data.mapper;

import com.accounting.ecommerce.data.dto.BillDTO;
import com.accounting.ecommerce.data.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMapper implements EntityMapper<Bill, BillDTO> {
    @Override
    public BillDTO toDTO(Bill bill) {
        return BillDTO.builder()
                .firstName(bill.getFirstName())
                .lastName(bill.getLastName())
                .email(bill.getEmail())
                .amount(bill.getAmount())
                .productName(bill.getProductName())
                .billNo(bill.getBillNo())
                .approvalStatus(bill.isApprovalStatus())
                .build();
    }

    @Override
    public Bill toEntity(BillDTO billDTO) {
        return Bill.builder()
                .firstName(billDTO.getFirstName())
                .lastName(billDTO.getLastName())
                .email(billDTO.getEmail())
                .amount(billDTO.getAmount())
                .productName(billDTO.getProductName())
                .billNo(billDTO.getBillNo())
                .build();
    }
}
