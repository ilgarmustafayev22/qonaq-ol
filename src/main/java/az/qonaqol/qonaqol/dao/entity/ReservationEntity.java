package az.qonaqol.qonaqol.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", length = 50, nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^[0-9\\+-]*$")
    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "participants_count", length = 3, nullable = false)
    private Integer participantsCount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonBackReference
    private EventEntity event;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
