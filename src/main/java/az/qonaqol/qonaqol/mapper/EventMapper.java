package az.qonaqol.qonaqol.mapper;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.model.dto.EventDetailsDTO;
import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.request.EventRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(source = "userId", target = "user.id")
    EventEntity toEntity(EventRequest request);


    EventEntity toEntity(EventDetailsDTO eventDetailsDTO);

    @Mapping(source = "user.id", target = "userId")
    EventDto toDto(EventEntity entity);

}
