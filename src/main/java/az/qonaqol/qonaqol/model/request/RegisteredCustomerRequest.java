package az.qonaqol.qonaqol.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCustomerRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private Integer participantsCount;

}
