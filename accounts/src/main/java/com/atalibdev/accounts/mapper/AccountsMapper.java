package com.atalibdev.accounts.mapper;

import com.atalibdev.accounts.dto.AccountDTO;
import com.atalibdev.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountDTO mapToAccountDTO(Accounts accounts, AccountDTO accountDTO) {
        accountDTO.setAccountNumber(accounts.getAccountNumber());
        accountDTO.setAccountType(accounts.getAccountType());
        accountDTO.setBranchAddress(accounts.getBranchAddress());
        return accountDTO;
    }

    public static Accounts matToAccount(AccountDTO accountDTO, Accounts accounts) {
        accounts.setAccountNumber(accountDTO.getAccountNumber());
        accounts.setAccountType(accountDTO.getAccountType());
        accounts.setBranchAddress(accountDTO.getBranchAddress());
        return accounts;
    }
}
