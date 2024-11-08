package az.qonaqol.qonaqol.model.request;

import jakarta.validation.constraints.*;
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
    @Size(max = 20)
    @Pattern(regexp = "^[0-9\\+-]*$")
    private String phoneNumber;

    @NotNull
    @Max(value = 999)
    private Integer participantsCount;

}
