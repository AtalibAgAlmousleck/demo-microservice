package com.atalibdev.loans.service;

import com.atalibdev.loans.dto.LoansDto;

public interface LoansService {

    void createLoan(String phoneNumber);
    LoansDto fetchLoan(String phoneNumber);
    boolean updateLoan(LoansDto loansDto);
    boolean deleteLoan(String phoneNumber);
}
