package com.atalibdev.loans.repository;

import com.atalibdev.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByPhoneNumber(String phoneNumber);
    Optional<Loans> findByLoanNumber(String loanNumber);
}
