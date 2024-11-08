package az.qonaqol.qonaqol.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GiftCardType {

    AZN_50("50 AZN"), AZN_100("100 AZN"), AZN_200("200 AZN");

    private final String value;

}
