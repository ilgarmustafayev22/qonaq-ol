package az.qonaqol.qonaqol.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationDto {

    String fullName;
    String email;
    String phoneNumber;
    Integer participantsCount;

}
