package org.sid.ebankservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sid.ebankservice.enums.AccountType;
import org.sid.ebankservice.model.Customer;

import java.util.Date;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private long customerId;
    @Transient
    private Customer customer;
}
