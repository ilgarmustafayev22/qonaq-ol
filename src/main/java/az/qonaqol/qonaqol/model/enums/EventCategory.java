package az.qonaqol.qonaqol.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventCategory {

    COUNTRY_LIFE("Kənd həyatı"),
    COOKING("Yemək hazırlama"),
    PAINTING("Rəssamlıq"),
    POTTERY("Dulusculuq"),
    CAMPING("Kamplar"),
    MUSIC("Musiqi");

    private final String value;

}
