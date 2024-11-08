package az.qonaqol.qonaqol.model.dto;

import az.qonaqol.qonaqol.model.enums.GiftCardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiftCardDto {

    @NotNull
    GiftCardType giftCardType;

    @NotBlank
    String usageInstructions;

}
