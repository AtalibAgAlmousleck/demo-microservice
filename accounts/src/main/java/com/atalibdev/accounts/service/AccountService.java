package com.atalibdev.accounts.service;

import com.atalibdev.accounts.dto.CustomerDTO;

public interface AccountService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchDetails(String phoneNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String phoneNumber);
}