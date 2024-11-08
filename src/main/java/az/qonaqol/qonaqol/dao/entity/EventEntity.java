package az.qonaqol.qonaqol.dao.entity;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
//@NamedEntityGraph(name = "Event.detail",
//        attributeNodes = {
//                @NamedAttributeNode("user"),
//                @NamedAttributeNode("reservation"),
//                @NamedAttributeNode("likes")
//        })
@Table(name = "events")
public class EventEntity extends  BaseEntity{


    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private EventCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private EventLanguage language;

    @Column(name = "event_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal eventPrice;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "event_start_time", nullable = false)
    private String eventStartTime;

    @Column(name = "event_end_time", nullable = false)
    private String eventEndTime;

    @Column(name = "event_location", nullable = false)
    private String eventLocation;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "main_photo_url")
    private String mainPhotoUrl;

    @Column(name = "view_count")
    private Long viewCount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "event_photo_urls", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "photo_urls")
    private List<String> photoUrls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationEntity> reservation;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeEntity> likes;
    
}
