package az.qonaqol.qonaqol.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "reservations")
public class ReservationEntity extends BaseEntity {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "participants_count", nullable = false)
    private Integer participantsCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonBackReference
    private EventEntity event;

    @ManyToMany(mappedBy = "reservations",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private Set<UserEntity> users;

    public Set<UserEntity> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

}
