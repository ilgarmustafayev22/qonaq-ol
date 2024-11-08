package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.dto.GiftCardDto;
import az.qonaqol.qonaqol.service.GiftCardService;
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
@RequestMapping("/api/gift-card")
public class GiftCardController {

    private final GiftCardService giftCardService;

    @PostMapping("/create-gift-card")
    public ResponseEntity<Void> createGiftCard(@RequestBody @Valid GiftCardDto giftCardDto) {
        giftCardService.createGiftCard(giftCardDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all-gift-cards")
    public ResponseEntity<List<GiftCardDto>> findAllGiftCards() {
        return ResponseEntity.ok(giftCardService.findAllGiftCards());
    }

    @GetMapping("/{giftCardId}")
    public ResponseEntity<GiftCardDto> findById(@Valid @PathVariable long giftCardId) {
        return ResponseEntity.ok(giftCardService.findGiftCardById(giftCardId));
    }

    @PutMapping("/{giftCardId}")
    public ResponseEntity<Void> updateGiftCard(@Valid @PathVariable long giftCardId,
                                               @RequestBody @Valid GiftCardDto giftCardDto) {
        giftCardService.updateGiftCard(giftCardId, giftCardDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{giftCardId}")
    public ResponseEntity<Void> deleteGiftCard(@Valid @PathVariable long giftCardId) {
        giftCardService.deleteGiftCard(giftCardId);
        return ResponseEntity.ok().build();
    }

}
