package com.accounting.ecommerce.repository;

import com.accounting.ecommerce.data.entity.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant, String> {
    Accountant findByEmail(String email);
}
