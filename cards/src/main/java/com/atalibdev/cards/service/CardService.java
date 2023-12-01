package com.atalibdev.cards.service;

import com.atalibdev.cards.dto.CardsDto;

public interface CardService {
    void createCard(String phoneNumber);
    CardsDto fetchCard(String phoneNumber);
    boolean updateCard(CardsDto cardsDto);
    boolean deleteCard(String phoneNumber);
}
