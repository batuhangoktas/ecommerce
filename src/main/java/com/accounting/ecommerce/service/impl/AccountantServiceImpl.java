package com.accounting.ecommerce.service.impl;

import com.accounting.ecommerce.data.dto.AccountantDTO;
import com.accounting.ecommerce.data.entity.Accountant;
import com.accounting.ecommerce.data.mapper.AccountantMapper;
import com.accounting.ecommerce.exception.AccountantAlreadyExistsException;
import com.accounting.ecommerce.repository.AccountantRepository;
import com.accounting.ecommerce.service.AccountantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountantServiceImpl implements AccountantService {
    @Autowired
    AccountantRepository accountantRepository;
    @Autowired
    AccountantMapper accountantMapper;

    @Override
    public AccountantDTO create(AccountantDTO accountantDTO) {
        if (accountantRepository.findByEmail(accountantDTO.getEmail()) != null)
            throw new AccountantAlreadyExistsException(accountantDTO.getEmail());

        Accountant accountant = accountantMapper.toEntity(accountantDTO);
        accountant.setPassword(new BCryptPasswordEncoder().encode(accountantDTO.getPassword()));
        return accountantMapper.toDTO(accountantRepository.save(accountant));
    }

    @Cacheable(value = "accountant", key = "#username")
    @Override
    public String getByUsername(String username) {
        return accountantRepository.findByEmail(username).getId();
    }
}
