package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;

public interface ReservationService {

    void createReservationForRegisteredCustomer(RegisteredCustomerRequest request);

    void createReservationForUnregisteredCustomer(UnRegisteredCustomerRequest request);

    //void cancelReservation(Long reservationId);
    // void getReservationsByEventId(Long eventId);

}
