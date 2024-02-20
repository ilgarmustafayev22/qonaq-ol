package az.qonaqol.qonaqol.model.dto;

import az.qonaqol.qonaqol.dao.entity.EventPhotoEntity;
import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDto {

    private Long id;
    private String eventName;
    private String description;
    private EventCategory category;
    private BigDecimal eventPrice;
    private LocalDate eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventLocation;
    private String contact;
    private Integer maxParticipants;

    //@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    //private List<EventPhotoEntity> photos = new ArrayList<>(5);

    private Long userId;

    private LocalDateTime createdDate;

}
