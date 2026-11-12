package org.sid.ebankservice.dtos;

import lombok.*;
import org.sid.ebankservice.enums.AccountType;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {


    private double balance;
    private AccountType type;
    private long customerId;
}
