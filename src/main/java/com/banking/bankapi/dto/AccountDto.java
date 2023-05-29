package com.banking.bankapi.dto;

import com.banking.bankapi.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {

    private String iban;

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .iban(account.getIban())
                .build();

    }

    public static Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .iban(accountDto.getIban())
                .build();
    }
}
