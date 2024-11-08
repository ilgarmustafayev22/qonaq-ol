package az.qonaqol.qonaqol.model.request;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.EAN;
import org.intellij.lang.annotations.RegExp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 50)
    private String eventName;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    private EventCategory category;

    @NotNull
    private EventLanguage language;

    @NotNull
    private BigDecimal eventPrice;

    @NotNull
    private LocalDate eventDate;

    @NotBlank
    private String eventStartTime;

    @NotBlank
    private String eventEndTime;

    @NotBlank
    @Size(max = 50)
    private String eventLocation;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[0-9\\+-]*$")
    private String contact;

}
