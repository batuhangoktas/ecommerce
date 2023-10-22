package com.accounting.ecommerce.service;

import com.accounting.ecommerce.data.dto.BillDTO;
import com.accounting.ecommerce.request.PaginationRequest;
import org.springframework.data.domain.Page;


public interface BillService {
    BillDTO create(BillDTO billDTO, String userName);

    Page<BillDTO> getAll(PaginationRequest paginationRequest, boolean approvalStatus);
}
