package org.sid.ebankservice.controllers;

import org.sid.ebankservice.dtos.BankAccountDTO;
import org.sid.ebankservice.dtos.BankAccountRequestDTO;
import org.sid.ebankservice.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountRestController {


    private BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("bankAccountsByCustomer/{id}")
    public List<BankAccountDTO> getBankAccountsByCustomer(@PathVariable Long id) {
        return bankAccountService.getBankAccountByCustomerId(id);
    }

    @GetMapping("bankAccounts/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountDTO addBankAccountById(@RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return bankAccountService.saveBankAccount(bankAccountRequestDTO);
    }
}
