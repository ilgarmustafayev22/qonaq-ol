package az.qonaqol.qonaqol.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventLanguage {

    AZERBAIJANI("Azərbaycan"),
    ENGLISH("English"),
    RUSSIAN("Русский");

    private final String value;

}
