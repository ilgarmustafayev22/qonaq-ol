package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.dto.GiftCardOrderDto;

import java.util.List;

public interface GiftCardOrderService {

   void createGiftCardOrder(GiftCardOrderDto giftCardOrderDto);

   List<GiftCardOrderDto> getGiftCardOrders();

   GiftCardOrderDto findGiftCardOrderById(Long id);

   void updateGiftCardOrder(Long id, GiftCardOrderDto giftCardOrderDto);

   void deleteGiftCardOrder(Long id);

}
