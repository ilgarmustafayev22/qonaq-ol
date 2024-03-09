package az.qonaqol.qonaqol.dao.entity;

import az.qonaqol.qonaqol.model.enums.GiftCardType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gift_card_orders")
public class GiftCardOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_name", length = 50, nullable = false)
    private String senderName;

    @Column(name = "sender_phone_number", length = 20, nullable = false)
    private String senderPhoneNumber;

    @Column(name = "receiver_name", length = 50, nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone_number", length = 20, nullable = false)
    private String receiverPhoneNumber;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "is_completed", nullable = false, columnDefinition = "boolean default false")
    private Boolean isCompleted;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "gift_card",referencedColumnName = "id")
    @JsonBackReference
    private GiftCardEntity giftCard;

}
