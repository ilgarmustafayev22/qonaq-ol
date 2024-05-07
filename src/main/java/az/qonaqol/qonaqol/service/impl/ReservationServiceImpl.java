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
import az.qonaqol.qonaqol.model.dto.ReservationDto;
import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;
import az.qonaqol.qonaqol.service.MailService;
import az.qonaqol.qonaqol.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final MailService mailService;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Long createReservationForRegisteredCustomer(RegisteredCustomerRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        ReservationEntity reservationEntity = reservationRepository.save(ReservationEntity.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .participantsCount(request.getParticipantsCount())
                .event(EventEntity.builder().id(request.getEventId()).build())
                .build());
        user.addReservation(reservationEntity);
        return reservationEntity.getId();
    }

    @Override
    @Transactional
    public void createReservationForUnregisteredCustomer(UnRegisteredCustomerRequest request) {
        reservationRepository.save(reservationMapper.toEntity(request));
    }

    @Override
    public List<ReservationDto> findByUserId(long userId) {
        return reservationRepository.findByUsers_Id(userId).stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findById(long userId) {
        return reservationRepository.findByUserId(userId);
    }


    @Override
    @Transactional
    public void deleteReservation(long id) {
        reservationRepository.deleteFromUsersReservationsByReservationId(id);
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByEventId(long id) {
        List<Long> reservationsIds = reservationRepository.findByEventId(id);
        reservationsIds.forEach(reservationsId -> {
            reservationRepository.deleteFromUsersReservationsByReservationId(reservationsId);
            reservationRepository.deleteById(reservationsId);
        });
    }

    @Override
    @Transactional
    public void deleteFromUsersReservationsByReservationId(long id) {
        reservationRepository.deleteFromUsersReservationsByReservationId(id);
    }

}
