package az.qonaqol.qonaqol.dao.entity;

import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name", length = 50, nullable = false)
    private String eventName;

    @Column(name = "description", nullable = false)
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

    @Column(name = "event_location", length = 50, nullable = false)
    private String eventLocation;

    @Pattern(regexp = "^[0-9\\+-]*$", message = "Contact information can only contain numbers, +, and -")
    @Column(name = "contact", length = 20, nullable = false)
    private String contact;

    @Column(name = "max_participants", length = 3, nullable = false)
    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<ReservationEntity> reservation;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "main_photo_url")
    private String mainPhotoUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "photo_urls")
    private List<String> photoUrls = new ArrayList<>(5);

}
