package com.accounting.ecommerce.data.mapper;

import com.accounting.ecommerce.data.dto.AccountantDTO;
import com.accounting.ecommerce.data.entity.Accountant;
import org.springframework.stereotype.Component;

@Component
public class AccountantMapper implements EntityMapper<Accountant, AccountantDTO> {
    @Override
    public AccountantDTO toDTO(Accountant accountant) {
        return AccountantDTO.builder()
                .firstName(accountant.getFirstName())
                .lastName(accountant.getLastName())
                .email(accountant.getEmail())
                .build();
    }

    @Override
    public Accountant toEntity(AccountantDTO accountantDTO) {
        return Accountant.builder()
                .firstName(accountantDTO.getFirstName())
                .lastName(accountantDTO.getLastName())
                .email(accountantDTO.getEmail())
                .build();
    }
}
