package az.qonaqol.qonaqol.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {

    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 10, min = 8)
    private String password;

    @Override
    public String toString() {
        return String.format("SigninRequest{email='%s', password='******'}", email);
    }

}
