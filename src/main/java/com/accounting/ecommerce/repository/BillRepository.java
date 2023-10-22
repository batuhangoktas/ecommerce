package com.accounting.ecommerce.repository;

import com.accounting.ecommerce.data.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    @Query("SELECT sum(b.amount) from Bill b where b.accountantId = ?1 and b.approvalStatus = true")
    Double totalApprovedAmountByAccountantId(String accountantId);

    Page<Bill> findAllByApprovalStatus(PageRequest pageRequest, boolean approvalStatus);
}
