package az.qonaqol.qonaqol.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiftCardOrderDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotBlank
    @Size(max = 50)
    String senderName;

    @NotBlank
    @Size(max = 20)
    String senderPhoneNumber;

    @NotBlank
    @Size(max = 50)
    String receiverName;

    @NotBlank
    @Size(max = 20)
    String receiverPhoneNumber;

    @NotBlank
    String message;

    @NotNull
    @JsonIgnore
    Long giftCardId;

}
