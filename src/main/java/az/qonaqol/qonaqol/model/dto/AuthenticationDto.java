package az.qonaqol.qonaqol.model.dto;

import az.qonaqol.qonaqol.security.jwt.TokenPair;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationDto {

    Long userId;
    TokenPair tokenPair;

}
