package org.sid.ebankservice.services;

import org.sid.ebankservice.dtos.BankAccountDTO;
import org.sid.ebankservice.dtos.BankAccountRequestDTO;
import org.sid.ebankservice.entity.BankAccount;
import org.sid.ebankservice.feign.CustomerRestClient;
import org.sid.ebankservice.mappers.BankAccountMapper;
import org.sid.ebankservice.repository.BankAccountRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper dtoMapper;
    private CustomerRestClient customerRestClient;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper dtoMapper, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.dtoMapper = dtoMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    @McpTool(description = "Get all Bank accounts")
    public List<BankAccountDTO> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        System.out.println("getAllBankAccounts : "+bankAccounts.size());
        return bankAccounts.stream().map(account-> dtoMapper.toBankAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    @McpTool(description = "Get Bank account by id")
    public BankAccountDTO getBankAccountById(@McpToolParam(description = "the bank account id ") String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Bank account not found") );
        BankAccountDTO bankAccountDTO=dtoMapper.toBankAccountDTO(bankAccount);
        bankAccountDTO.setCustomer(customerRestClient.getCustomerById(bankAccountDTO.getCustomerId()));
        return bankAccountDTO;
    }

    @Override
    @McpTool(description = "Get bank account y customer id")
    public List<BankAccountDTO> getBankAccountByCustomerId(@McpToolParam(description = "ths customer id") Long customerId) {
        List<BankAccount> banakAccounts= bankAccountRepository.findByCustomerId(customerId);
        return banakAccounts.stream().map(account-> dtoMapper.toBankAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    @McpTool(description = "save new bank account")
    public BankAccountDTO saveBankAccount(@McpToolParam(description = "the bank account to save (balance,type, customerId)") BankAccountRequestDTO bankAccountRequestDTO) {
        try{
            BankAccount bankAccount = dtoMapper.toBankAccount(bankAccountRequestDTO);
            bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
            saveBankAccount.setCustomer(customerRestClient.getCustomerById(saveBankAccount.getCustomerId()));
            return dtoMapper.toBankAccountDTO(saveBankAccount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
