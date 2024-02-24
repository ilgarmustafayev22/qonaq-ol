package az.qonaqol.qonaqol.model.dto;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDto {

    String eventName;
    String description;
    EventCategory category;
    EventLanguage language;
    BigDecimal eventPrice;
    LocalDate eventDate;
    String eventStartTime;
    String eventEndTime;
    String eventLocation;
    String contact;
    Integer maxParticipants;
    List<String> photoUrls;

}
