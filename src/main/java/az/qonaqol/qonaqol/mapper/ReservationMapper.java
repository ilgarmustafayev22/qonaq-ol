package az.qonaqol.qonaqol.mapper;

import az.qonaqol.qonaqol.dao.entity.ReservationEntity;
import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    @Mapping(source = "eventId", target = "event.id")
    ReservationEntity toEntity(UnRegisteredCustomerRequest request);

}
