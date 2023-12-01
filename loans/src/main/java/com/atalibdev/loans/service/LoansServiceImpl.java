package com.atalibdev.loans.service;

import com.atalibdev.loans.constants.LoansConstants;
import com.atalibdev.loans.dto.LoansDto;
import com.atalibdev.loans.entity.Loans;
import com.atalibdev.loans.exception.LoansAlreadyExistException;
import com.atalibdev.loans.exception.ResourceNotFoundException;
import com.atalibdev.loans.mapper.LoansMapper;
import com.atalibdev.loans.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements LoansService {
    private final LoansRepository loansRepository;

    @Override
    public void createLoan(String phoneNumber) {
        Optional<Loans> optionalLoans = loansRepository
                .findByLoanNumber(phoneNumber);
        if (optionalLoans.isPresent())
            throw new LoansAlreadyExistException("Loan already registered with given mobileNumber" + phoneNumber);
        loansRepository.save(createNewLoans(phoneNumber));
    }

    private Loans createNewLoans(String phoneNumber) {
        Loans loans = new Loans();
        long randomLoansNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomLoansNumber));
        loans.setPhoneNumber(phoneNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }

    @Override
    public LoansDto fetchLoan(String phoneNumber) {
        Loans loans = loansRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", phoneNumber)
        );
        return LoansMapper.mapToLoansDTO(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToloans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String phoneNumber) {
        Loans loans = loansRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", phoneNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
