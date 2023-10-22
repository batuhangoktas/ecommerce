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
@Table(name = "accountant")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Accountant {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Id
    @UuidGenerator
    private String id;

    public void setPassword(String password) {
        this.password = password;
    }
}
