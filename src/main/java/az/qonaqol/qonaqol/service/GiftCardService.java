package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.dto.GiftCardDto;
import az.qonaqol.qonaqol.model.enums.GiftCardType;

import java.util.List;

public interface GiftCardService {

    void createGiftCard(GiftCardDto giftCardDto);

    GiftCardDto findGiftCardById(Long id);

    List<GiftCardDto> findAllGiftCards();

    void updateGiftCard(Long id, GiftCardDto giftCardDto);

    void deleteGiftCard(Long id);

}
