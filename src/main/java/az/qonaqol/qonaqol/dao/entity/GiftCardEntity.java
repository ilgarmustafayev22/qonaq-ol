package az.qonaqol.qonaqol.dao.entity;

import az.qonaqol.qonaqol.model.enums.GiftCardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gift_cards")
public class GiftCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "gift_card_type", nullable = false)
    private GiftCardType giftCardType;

    @Column(name = "usage_instructions", nullable = false)
    private String usageInstructions;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "giftCard", cascade = CascadeType.ALL)
    private List<GiftCardOrderEntity> giftCardOrder;

}
