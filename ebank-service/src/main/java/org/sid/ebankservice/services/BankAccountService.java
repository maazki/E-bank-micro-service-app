package org.sid.ebankservice.services;

import org.sid.ebankservice.dtos.BankAccountDTO;
import org.sid.ebankservice.dtos.BankAccountRequestDTO;
import org.sid.ebankservice.entity.BankAccount;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDTO> getAllBankAccounts();
    BankAccountDTO getBankAccountById(String id);
    List<BankAccountDTO> getBankAccountByCustomerId(Long customerId);
    BankAccountDTO saveBankAccount(BankAccountRequestDTO bankAccountRequestDTO);
}
