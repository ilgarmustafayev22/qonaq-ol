package az.qonaqol.qonaqol.mapper;

import az.qonaqol.qonaqol.dao.entity.GiftCardEntity;
import az.qonaqol.qonaqol.model.dto.GiftCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GiftCardMapper {

    GiftCardEntity toEntity(GiftCardDto dto);

    GiftCardDto toDto(GiftCardEntity entity);

}
