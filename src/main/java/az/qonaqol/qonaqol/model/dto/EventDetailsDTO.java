package az.qonaqol.qonaqol.model.dto;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class EventDetailsDTO {

    private Long id;
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
    private String mainPhotoUrl;
    private Long viewCount;
    private List<String> photoUrls;

    public EventDetailsDTO(Long id, String eventName, String description, EventCategory category, EventLanguage language, BigDecimal eventPrice, LocalDate eventDate, String eventStartTime, String eventEndTime, String eventLocation, String contact, String mainPhotoUrl, Long viewCount, List<String> photoUrls) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.category = category;
        this.language = language;
        this.eventPrice = eventPrice;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventLocation = eventLocation;
        this.contact = contact;
        this.mainPhotoUrl = mainPhotoUrl;
        this.viewCount = viewCount;
        this.photoUrls = photoUrls;
    }

    public EventDetailsDTO() {
    }

}
