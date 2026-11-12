package org.sid.ebankservice;

import org.sid.ebankservice.dtos.BankAccountRequestDTO;
import org.sid.ebankservice.entity.BankAccount;
import org.sid.ebankservice.enums.AccountType;
import org.sid.ebankservice.repository.BankAccountRepository;
import org.sid.ebankservice.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLinerRunner(BankAccountService BankAccountService) {
        return args ->{
            for(int i=1;i<4;i++){
                for(int j=0;j<5;j++){
                    BankAccountRequestDTO bankAccount =  BankAccountRequestDTO.builder()

                            .type(Math.random()>0.5? AccountType.CURRENT : AccountType.SAVING)
                            .balance(1000+Math.random()*100)
                            .customerId(i)
                            .build();
                    BankAccountService.saveBankAccount(bankAccount);
                }
            }
        };
    }

}
