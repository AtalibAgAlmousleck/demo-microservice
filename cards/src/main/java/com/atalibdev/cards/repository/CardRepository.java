package com.atalibdev.cards.repository;

import com.atalibdev.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByPhoneNumber(String phoneNumber);
    Optional<Cards> findByCardNumber(String cardNumber);
}
