package com.atalibdev.cards.service;

import com.atalibdev.cards.constants.CardsConstants;
import com.atalibdev.cards.dto.CardsDto;
import com.atalibdev.cards.entity.Cards;
import com.atalibdev.cards.exception.CardAlreadyExistsException;
import com.atalibdev.cards.exception.ResourceNotFoundException;
import com.atalibdev.cards.mapper.CardsMapper;
import com.atalibdev.cards.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public void createCard(String phoneNumber) {
        Optional<Cards> optionalCards = cardRepository.findByPhoneNumber(phoneNumber);
        if (optionalCards.isPresent())
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+ phoneNumber);
        cardRepository.save(createNewCard(phoneNumber));
    }

    @Override
    public CardsDto fetchCard(String phoneNumber) {
        Cards cards = cardRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", phoneNumber));
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String phoneNumber) {
        Cards cards = cardRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", phoneNumber));
        cardRepository.deleteById(cards.getCardId());
        return true;
    }

    // private method create new card
    private Cards createNewCard(String phoneNumber) {
        Cards cards = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        cards.setCardNumber(Long.toString(randomCardNumber));
        cards.setPhoneNumber(phoneNumber);
        cards.setCardType(CardsConstants.CREDIT_CARD);
        cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return cards;
    }
}
