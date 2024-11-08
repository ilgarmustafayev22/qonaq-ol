package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.dto.ReservationDto;
import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;

import java.util.List;

public interface ReservationService {

    Long createReservationForRegisteredCustomer(RegisteredCustomerRequest request);
    void createReservationForUnregisteredCustomer(UnRegisteredCustomerRequest request);
    List<ReservationDto> findByUserId(long userId);
    List<Long> findById(long userId);
    void deleteReservation(long id);
    void deleteByEventId(long id);
    void deleteFromUsersReservationsByReservationId(long id);

    //void cancelReservation(Long reservationId);
    // void getReservationsByEventId(Long eventId);

}
