package com.accounting.ecommerce.service.impl;

import com.accounting.ecommerce.data.dto.BillDTO;
import com.accounting.ecommerce.data.entity.Bill;
import com.accounting.ecommerce.data.mapper.BillMapper;
import com.accounting.ecommerce.event.application.CustomSpringEventPublisher;
import com.accounting.ecommerce.repository.BillRepository;
import com.accounting.ecommerce.request.PaginationRequest;
import com.accounting.ecommerce.service.AccountantService;
import com.accounting.ecommerce.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    @Autowired
    AccountantService accountantService;
    @Autowired
    BillRepository billRepository;
    @Autowired
    BillMapper billMapper;

    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;

    @Value("${creditlimit}")
    int creditLimit;

    @Override
    public BillDTO create(BillDTO billDTO, String username) {
        String accountantId = accountantService.getByUsername(username);
        Bill bill = billMapper.toEntity(billDTO);
        bill.setAccountantId(accountantId);
        bill.setApprovalStatus(false);

        Double totalAmount = billRepository.totalApprovedAmountByAccountantId(accountantId);
        totalAmount = totalAmount == null ? 0 : totalAmount;

        if (totalAmount + billDTO.getAmount() > creditLimit) {
            customSpringEventPublisher.publishBillEvent("Bill unapproved: " + bill.getBillNo() + ", " + "Accountant: " + username);
            return billMapper.toDTO(billRepository.save(bill));
        }

        bill.setApprovalStatus(true);
        return billMapper.toDTO(billRepository.save(bill));
    }

    @Override
    public Page<BillDTO> getAll(PaginationRequest paginationRequest, boolean approvalStatus) {
        return billRepository.findAllByApprovalStatus(PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()), approvalStatus).map(billMapper::toDTO);
    }
}
