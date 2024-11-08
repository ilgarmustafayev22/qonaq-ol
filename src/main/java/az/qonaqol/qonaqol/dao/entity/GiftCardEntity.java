package az.qonaqol.qonaqol.dao.entity;

import az.qonaqol.qonaqol.model.enums.GiftCardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "gift_cards")
public class GiftCardEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "gift_card_type", nullable = false)
    private GiftCardType giftCardType;

    @Column(name = "usage_instructions", nullable = false)
    private String usageInstructions;

    @OneToMany(mappedBy = "giftCard", cascade = CascadeType.ALL)
    private List<GiftCardOrderEntity> giftCardOrder;

}
