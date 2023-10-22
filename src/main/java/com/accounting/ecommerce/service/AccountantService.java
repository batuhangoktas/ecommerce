package com.accounting.ecommerce.service;

import com.accounting.ecommerce.data.dto.AccountantDTO;

public interface AccountantService {
    AccountantDTO create(AccountantDTO accountantDTO);

    String getByUsername(String username);
}
