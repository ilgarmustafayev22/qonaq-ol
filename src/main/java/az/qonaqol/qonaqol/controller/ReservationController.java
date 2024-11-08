package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.dto.ReservationDto;
import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;
import az.qonaqol.qonaqol.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create-reservation-registered")
    public ResponseEntity<Long> createReservation(@RequestBody @Valid RegisteredCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservationForRegisteredCustomer(request));
    }

    @PostMapping("/create-reservation-unregistered")
    public ResponseEntity<Void> createReservationUnregistered(@RequestBody @Valid UnRegisteredCustomerRequest request) {
        reservationService.createReservationForUnregisteredCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReservationDto>> findByUserId(@Valid @PathVariable long userId) {
        return ResponseEntity.ok(reservationService.findByUserId(userId));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Long>> findById(@Valid @PathVariable long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@Valid @PathVariable long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-eventId/{eventId}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable long eventId){
        reservationService.deleteByEventId(eventId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users-reservations/{reservationId}")
    public ResponseEntity<Void> deleteFromUsersReservationsByReservationId(@Valid @PathVariable long reservationId){
        reservationService.deleteFromUsersReservationsByReservationId(reservationId);
        return ResponseEntity.noContent().build();
    }

}
