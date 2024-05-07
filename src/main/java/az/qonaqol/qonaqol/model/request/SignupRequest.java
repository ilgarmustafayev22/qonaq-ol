package az.qonaqol.qonaqol.model.request;

import az.qonaqol.qonaqol.model.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 10, min = 8)
    private String password;

    @NotBlank
    @Size(max = 10, min = 8)
    private String confirmPassword;

    @Override
    public String toString() {
        return new StringBuilder("SignupRequest{")
                .append("fullName='").append(fullName).append('\'')
                .append(", email='").append(email).append('\'')
                .append(", password='").append("******").append('\'')
                .append('}')
                .toString();
    }

}
