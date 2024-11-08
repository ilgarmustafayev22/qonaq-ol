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
import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.dto.ReservationDto;
import az.qonaqol.qonaqol.model.request.RegisteredCustomerRequest;
import az.qonaqol.qonaqol.model.request.UnRegisteredCustomerRequest;
import az.qonaqol.qonaqol.service.EventService;
import az.qonaqol.qonaqol.service.MailService;
import az.qonaqol.qonaqol.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private static final String EMAIL_TEMPLATE_FOR_CUSTOMER_USER = "Salam, hörmətli {0},\n\n" +
            "Tədbirimizə olan marağınız və rezervasiyanız üçün təşəkkür edirik!\n " +
            "Rezervasiya detallarınız:\n\n" +
            "Tədbirin adı: {1}\n" +
            "Tarix: {2}\n" +
            "Məkan: {3}\n" +
            "İştirakçı sayı: {4}\n\n" +
            "Tədbir ilə bağlı  sualınız olarsa, zəhmət olmasa bizimlə əlaqə saxlamağa çəkinməyin. \n" +
            "Sizi tədbirimizdə görmək üçün səbrsizlənirik!\n\n" +
            "Hörmətlə: \n\n" +
            "https://qonaqol.az komandası";

    private static final String EMAIL_TEMPLATE_FOR_HOST_USER = "Salam, hörmətli {0},\n\n" +
            "Tədbirinizə yeni bir rezervasiya qəbul edildi. Detallar:\n\n" +
            "Tədbir Adı: {1}\n" +
            "Tarix: {2}\n" +
            "Müştərinin emaili: {3}\n" +
            "Müştərinin nömrəsi: {4}\n" +
            "Tədbirlə bağlı hərhansı bir dəyişiklik olacaqsa, zəhmət olmasa bizə məlumat verin.\n\n" +
            "Uğurlar arzu edirik!\n\n" +
            "Hörmətlə,\n\n" +
            "https://qonaqol.az komandası";

    private final MailService mailService;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public Long createReservationForRegisteredCustomer(RegisteredCustomerRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        sendEmail(request.getEventId(),
                user.getFullName(),
                request.getParticipantsCount(),
                user.getEmail(),
                request.getPhoneNumber());

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
    @Transactional(value = Transactional.TxType.REQUIRED)
    public void createReservationForUnregisteredCustomer(UnRegisteredCustomerRequest request) {
        reservationRepository.save(reservationMapper.toEntity(request));
        sendEmail(request.getEventId(),
                request.getFullName(),
                request.getParticipantsCount(),
                request.getEmail(),
                request.getPhoneNumber());
    }

    private void sendEmail(Long eventId,
                           String fullName,
                           Integer participantsCount,
                           String customerUserEmail,
                           String customerUserPhoneNumber) {

        EventEntity eventEntity = eventRepository.findById(eventId).orElseThrow();
        UserEntity userEntity = userRepository.findById(eventEntity.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + eventEntity.getUser().getId()));

        String emailBodyForCustomerUser = MessageFormat.format(
                EMAIL_TEMPLATE_FOR_CUSTOMER_USER,
                fullName,
                eventEntity.getEventName(),
                eventEntity.getEventDate().toString(),
                eventEntity.getEventLocation(),
                participantsCount.toString()
        );

        mailService.sendMail(customerUserEmail, "Tədbir Rezervasiyanız Qəbul edildi!\n", emailBodyForCustomerUser);

        String emailBodyForHostUser = MessageFormat.format(
                EMAIL_TEMPLATE_FOR_HOST_USER,
                userEntity.getFullName(),
                eventEntity.getEventName(),
                eventEntity.getEventDate().toString(),
                customerUserEmail,
                customerUserPhoneNumber
        );
        mailService.sendMail(userEntity.getEmail(), "Yeni Bir Rezervasiyanız Var!\n", emailBodyForHostUser);
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
