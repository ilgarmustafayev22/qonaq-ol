package az.qonaqol.qonaqol.mapper;

import az.qonaqol.qonaqol.dao.entity.GiftCardOrderEntity;
import az.qonaqol.qonaqol.model.dto.GiftCardOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GiftCardOrderMapper {

    GiftCardOrderEntity toEntity(GiftCardOrderDto giftCardOrderDto);

    GiftCardOrderDto toDto(GiftCardOrderEntity giftCardOrderEntity);

}
