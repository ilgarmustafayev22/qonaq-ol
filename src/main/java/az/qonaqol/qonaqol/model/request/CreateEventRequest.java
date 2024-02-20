package az.qonaqol.qonaqol.model.request;

import az.qonaqol.qonaqol.dao.entity.EventPhotoEntity;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    private String eventName;
    private String description;
    private EventCategory category;
    private EventLanguage language;
    private BigDecimal eventPrice;
    private LocalDate eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventLocation;
    private String contact;
    private Integer maxParticipants;
    private Long userId;

}
