package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.AccountDto;
import com.banking.bankapi.exceptions.OperationNonPermittedException;
import com.banking.bankapi.models.Account;
import com.banking.bankapi.repositories.AccountRepository;
import com.banking.bankapi.services.AccountService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        //block iban update -> iban cannot be changed once created
        if (dto.getId() != null) {
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "account",
                    "Update not permitted"
            );
        }
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto); //transform dto to entity
        //GENERATE RANDOM IBAN
        account.setIban(generateRandomIban());
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(account -> AccountDto.fromEntity(account))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(account -> AccountDto.fromEntity(account))
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete account
        accountRepository.deleteById(id);
    }

    private String generateRandomIban() {
        //generate iban (We use iban4j dependency)
        String iban = Iban.random(CountryCode.CA).toFormattedString();
        //check if iban already exists
        boolean ibanExists = accountRepository.findByIban(iban).isPresent();

        //if exists -> generate another one
        if (ibanExists) {
            generateRandomIban();
        }

        //if not exists -> return iban

        return iban;
    }
}
