package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.dao.entity.ReservationEntity;
import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.EventRepository;
import az.qonaqol.qonaqol.dao.repository.ReservationRepository;
import az.qonaqol.qonaqol.dao.repository.UserRepository;
import az.qonaqol.qonaqol.exception.EventNotFoundException;
import az.qonaqol.qonaqol.exception.UserNotFoundException;
import az.qonaqol.qonaqol.mapper.ReservationMapper;
import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;
import az.qonaqol.qonaqol.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void createReservationForRegisteredCustomer(RegisteredCustomerRequest request) {
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + request.getEventId()));

        if (event.getMaxParticipants() < request.getParticipantsCount()) {
            throw new IllegalArgumentException("Participants count is more than max participants count.");
        }

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        reservationRepository.save(ReservationEntity.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .participantsCount(request.getParticipantsCount())
                .user(UserEntity.builder().id(request.getUserId()).build())
                .event(EventEntity.builder().id(request.getEventId()).build()).build());
    }

    @Override
    @Transactional
    public void createReservationForUnregisteredCustomer(UnRegisteredCustomerRequest request) {
        reservationRepository.save(reservationMapper.toEntity(request));
    }

}
