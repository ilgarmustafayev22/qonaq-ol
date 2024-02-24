package az.qonaqol.qonaqol.mapper;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.enums.EventLanguage;
import az.qonaqol.qonaqol.model.request.EventRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(source = "userId", target = "user.id")
    EventEntity toEntity(EventRequest request);

    EventDto toDto(EventEntity entity);

}
