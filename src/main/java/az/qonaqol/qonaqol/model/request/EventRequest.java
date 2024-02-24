package az.qonaqol.qonaqol.model.request;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.EAN;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String eventName;

    @NotBlank
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
    private String eventLocation;

    @NotBlank
    private String contact;

    @NotNull
    private Integer maxParticipants;

}
