package az.qonaqol.qonaqol.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnRegisteredCustomerRequest {

    @NotNull
    private Long eventId;

    @NotBlank
    @Size(max = 50)
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9\\+-]*$")
    private String phoneNumber;

    @NotNull
    @Max(value = 999)
    private Integer participantsCount;

}
