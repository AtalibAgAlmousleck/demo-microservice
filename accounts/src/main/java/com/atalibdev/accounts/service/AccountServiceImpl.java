package com.atalibdev.accounts.service;

import com.atalibdev.accounts.dto.AccountDTO;
import com.atalibdev.accounts.exception.ResourceNotFoundException;
import com.atalibdev.accounts.mapper.AccountsMapper;
import com.atalibdev.accounts.repository.AccountsRepository;
import com.atalibdev.accounts.repository.CustomerRepository;
import com.atalibdev.accounts.constants.AccountsConstants;
import com.atalibdev.accounts.dto.CustomerDTO;
import com.atalibdev.accounts.entity.Accounts;
import com.atalibdev.accounts.entity.Customer;
import com.atalibdev.accounts.exception.CustomerAlreadyExistException;
import com.atalibdev.accounts.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper
                .mapToCustomer(customerDTO, new Customer());
        Optional<Customer> check = customerRepository
                .findByEmail(customerDTO.getEmail());
        if (check.isPresent())
            throw new CustomerAlreadyExistException("Customer with the given details taken.");
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Developer");
        Customer saveCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(saveCustomer));
    }

    @Override
    public CustomerDTO fetchDetails(String phoneNumber) {
       Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "phone number", phoneNumber
                ));
       Accounts accounts = accountsRepository
               .findByCustomerId(customer.getCustomerId()).orElseThrow(
                       () -> new ResourceNotFoundException(
                               "Account", "customerId", customer.getCustomerId().toString())
               );
       CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
       customerDTO.setAccountDTO(AccountsMapper.mapToAccountDTO(accounts, new AccountDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if (accountDTO != null) {
            Accounts accounts =  accountsRepository.findById(accountDTO.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Account", "AccountNumber", accountDTO.getAccountType().toString()
                    ));
            AccountsMapper.matToAccount(accountDTO, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Customer", "CustomerId", customerId.toString()
                    ));
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Phone number", phoneNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount .setCreatedBy("Developer");
        return newAccount;
    }
}
