package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.GiftCardEntity;
import az.qonaqol.qonaqol.dao.repository.GiftCardRepository;
import az.qonaqol.qonaqol.exception.GiftCardNotFoundException;
import az.qonaqol.qonaqol.mapper.GiftCardMapper;
import az.qonaqol.qonaqol.model.dto.GiftCardDto;
import az.qonaqol.qonaqol.service.GiftCardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftCardServiceImpl implements GiftCardService {

    private final GiftCardMapper giftCardMapper;
    private final GiftCardRepository giftCardRepository;

    @Override
    @Transactional
    public void createGiftCard(GiftCardDto giftCardDto) {
        giftCardRepository.save(giftCardMapper.toEntity(giftCardDto));
    }

    @Override
    public GiftCardDto findGiftCardById(Long id) {
        return giftCardRepository.findById(id)
                .map(giftCardMapper::toDto)
                .orElseThrow(() -> new GiftCardNotFoundException("Gift card not found with id: " + id));
    }

    @Override
    public List<GiftCardDto> findAllGiftCards() {
        return giftCardRepository.findAll().stream()
                .map(giftCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateGiftCard(Long id, GiftCardDto giftCardDto) {
        GiftCardEntity giftCardEntity = giftCardRepository.findById(id)
                .orElseThrow(() -> new GiftCardNotFoundException("Gift card not found with id: " + id));
        giftCardEntity.setGiftCardType(giftCardDto.getGiftCardType());
        giftCardEntity.setUsageInstructions(giftCardDto.getUsageInstructions());
        giftCardEntity.setUpdatedAt(LocalDateTime.now());
        giftCardRepository.save(giftCardEntity);
    }

    @Override
    @Transactional
    public void deleteGiftCard(Long id) {
        giftCardRepository.findById(id).ifPresent(giftCardRepository::delete);
    }

}
