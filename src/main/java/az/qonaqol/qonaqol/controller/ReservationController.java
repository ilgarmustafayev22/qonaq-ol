package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;
import az.qonaqol.qonaqol.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create-reservation-registered")
    public ResponseEntity<Void> createReservation(@RequestBody @Valid RegisteredCustomerRequest request) {
        reservationService.createReservationForRegisteredCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/create-reservation-unregistered")
    public ResponseEntity<Void> createReservationUnregistered(@RequestBody @Valid UnRegisteredCustomerRequest request) {
        reservationService.createReservationForUnregisteredCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
