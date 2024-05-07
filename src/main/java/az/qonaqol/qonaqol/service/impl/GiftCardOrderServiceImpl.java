package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.GiftCardEntity;
import az.qonaqol.qonaqol.dao.entity.GiftCardOrderEntity;
import az.qonaqol.qonaqol.dao.repository.GiftCardOrderRepository;
import az.qonaqol.qonaqol.exception.GiftCardOrderNotFoundException;
import az.qonaqol.qonaqol.mapper.GiftCardOrderMapper;
import az.qonaqol.qonaqol.model.dto.GiftCardOrderDto;
import az.qonaqol.qonaqol.service.GiftCardOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCardOrderServiceImpl implements GiftCardOrderService {

    private final GiftCardOrderMapper giftCardOrderMapper;
    private final GiftCardOrderRepository giftCardOrderRepository;

    @Override
    @Transactional
    public void createGiftCardOrder(GiftCardOrderDto giftCardOrderDto) {
        giftCardOrderRepository.save(GiftCardOrderEntity.builder()
                .senderName(giftCardOrderDto.getSenderName())
                .senderPhoneNumber(giftCardOrderDto.getSenderPhoneNumber())
                .receiverName(giftCardOrderDto.getReceiverName())
                .receiverPhoneNumber(giftCardOrderDto.getReceiverPhoneNumber())
                .isCompleted(false)
                .giftCard(GiftCardEntity.builder().id(giftCardOrderDto.getGiftCardId()).build())
                .message(giftCardOrderDto.getMessage())
                .build());
    }

    @Override
    public List<GiftCardOrderDto> getGiftCardOrders() {
        return giftCardOrderRepository.findAll().stream()
                .map(giftCardOrderMapper::toDto)
                .toList();
    }

    @Override
    public GiftCardOrderDto findGiftCardOrderById(Long id) {
        return giftCardOrderRepository.findById(id)
                .map(giftCardOrderMapper::toDto)
                .orElseThrow(() -> new GiftCardOrderNotFoundException("Gift card order not found with id: " + id));
    }

    @Override
    @Transactional
    public void updateGiftCardOrder(Long id, GiftCardOrderDto giftCardOrderDto) {
        giftCardOrderRepository.findById(id)
                .map(giftCardOrder -> {
                    giftCardOrder.setSenderName(giftCardOrderDto.getSenderName());
                    giftCardOrder.setSenderPhoneNumber(giftCardOrderDto.getSenderPhoneNumber());
                    giftCardOrder.setReceiverName(giftCardOrderDto.getReceiverName());
                    giftCardOrder.setReceiverPhoneNumber(giftCardOrderDto.getReceiverPhoneNumber());
                    giftCardOrder.setMessage(giftCardOrderDto.getMessage());
                    giftCardOrder.setUpdatedAt(LocalDateTime.now());
                    return giftCardOrderRepository.save(giftCardOrder);
                })
                .orElseThrow(() -> new GiftCardOrderNotFoundException("Gift card order not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteGiftCardOrder(Long id) {
        giftCardOrderRepository.deleteById(id);
    }

}
