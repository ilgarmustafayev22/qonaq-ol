package az.qonaqol.qonaqol.model.enums;

import lombok.RequiredArgsConstructor;

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
