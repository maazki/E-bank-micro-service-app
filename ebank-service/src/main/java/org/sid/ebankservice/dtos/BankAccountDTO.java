package org.sid.ebankservice.dtos;

import lombok.*;
import org.sid.ebankservice.enums.AccountType;
import org.sid.ebankservice.model.Customer;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountDTO {

    private String id;
    private Date createdAt;
    private double balance;
    private AccountType type;
    private long customerId;
    private Customer customer;
}
