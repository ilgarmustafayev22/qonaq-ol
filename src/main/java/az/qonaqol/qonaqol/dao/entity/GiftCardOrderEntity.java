package az.qonaqol.qonaqol.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "gift_card_orders")
public class GiftCardOrderEntity extends BaseEntity {

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "sender_phone_number", nullable = false)
    private String senderPhoneNumber;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone_number", nullable = false)
    private String receiverPhoneNumber;

    @Column(name = "message", length = 500, nullable = false)
    private String message;

    @Column(name = "is_completed", columnDefinition = "boolean default false")
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "gift_card_id", referencedColumnName = "id")
    @JsonBackReference
    private GiftCardEntity giftCard;

}
