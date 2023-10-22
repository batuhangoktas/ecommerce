package com.accounting.ecommerce.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "bill")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @UuidGenerator
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Double amount;
    private String productName;
    private boolean approvalStatus;
    private String billNo;
    private String accountantId;

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setAccountantId(String accountantId) {
        this.accountantId = accountantId;
    }
}
