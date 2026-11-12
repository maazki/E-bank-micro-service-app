package org.sid.ebankservice.mappers;

import org.sid.ebankservice.dtos.BankAccountDTO;
import org.sid.ebankservice.dtos.BankAccountRequestDTO;
import org.sid.ebankservice.entity.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccount toBankAccount(BankAccountRequestDTO requestDto) {
        BankAccount bankAccount =new BankAccount();
        BeanUtils.copyProperties(requestDto,bankAccount);
        return bankAccount;
    }

    public BankAccountDTO toBankAccountDTO(BankAccount entity) {
        BankAccountDTO banaAccountDTO= new BankAccountDTO();

        BeanUtils.copyProperties(entity,banaAccountDTO);

        return banaAccountDTO;
    }
}
