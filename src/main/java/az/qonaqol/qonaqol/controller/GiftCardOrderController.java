package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.dto.GiftCardOrderDto;
import az.qonaqol.qonaqol.service.GiftCardOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gift-card-order")
public class GiftCardOrderController {

    private final GiftCardOrderService giftCardOrderService;

    @PostMapping("/create-gift-card-order")
    public ResponseEntity<Void> createGiftCardOrder(@RequestBody @Valid GiftCardOrderDto giftCardOrderDto) {
        giftCardOrderService.createGiftCardOrder(giftCardOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-gift-card-orders")
    public ResponseEntity<List<GiftCardOrderDto>> getGiftCardOrders() {
        return ResponseEntity.ok(giftCardOrderService.getGiftCardOrders());
    }

    @GetMapping("/get-gift-card-order/{id}")
    public ResponseEntity<GiftCardOrderDto> getGiftCardOrder(@PathVariable Long id) {
        return ResponseEntity.ok(giftCardOrderService.findGiftCardOrderById(id));
    }

    @PutMapping("/update-gift-card-order/{id}")
    public ResponseEntity<Void> updateGiftCardOrder(@PathVariable Long id,
                                                    @RequestBody @Valid GiftCardOrderDto giftCardOrderDto) {
        giftCardOrderService.updateGiftCardOrder(id, giftCardOrderDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-gift-card-order/{id}")
    public ResponseEntity<Void> deleteGiftCardOrder(@PathVariable Long id) {
        giftCardOrderService.deleteGiftCardOrder(id);
        return ResponseEntity.ok().build();
    }

}
