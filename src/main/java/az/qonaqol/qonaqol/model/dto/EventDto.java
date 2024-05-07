package az.qonaqol.qonaqol.model.dto;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDto {

    Long id;
    Long userId;
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
    String mainPhotoUrl;
    Long viewCount;
    List<String> photoUrls;

}
