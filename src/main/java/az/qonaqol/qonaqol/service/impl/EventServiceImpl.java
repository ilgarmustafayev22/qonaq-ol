package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.dao.repository.EventRepository;
import az.qonaqol.qonaqol.exception.EventNotFoundException;
import az.qonaqol.qonaqol.mapper.EventMapper;
import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.request.EventRequest;
import az.qonaqol.qonaqol.service.EventService;
import az.qonaqol.qonaqol.service.ReservationService;
import az.qonaqol.qonaqol.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    public static final String PHOTO_URL = "https://qonaqol.s3.eu-north-1.amazonaws.com/";
    private final FileUtil fileUtil;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final ReservationService reservationService;

    private static String apply(MultipartFile file) {
        return PHOTO_URL + file.getOriginalFilename();
    }

    @Override
    @Transactional
    public Long createEvent(EventRequest eventRequest, MultipartFile photo, MultipartFile[] photos) {
        EventEntity eventEntity = eventMapper.toEntity(eventRequest);
        eventEntity.setViewCount(0L);
        EventEntity event = eventRepository.save(eventEntity);
        uploadPhotos(event, photo, photos);
        return eventEntity.getId();
    }

    @Override
    @Transactional
    public Long createEventTest(EventRequest eventRequest) {
        EventEntity eventEntity = EventEntity.builder()
                .eventName(eventRequest.getEventName())
                .description(eventRequest.getDescription())
                .category(eventRequest.getCategory())
                .language(eventRequest.getLanguage())
                .eventPrice(eventRequest.getEventPrice())
                .eventDate(eventRequest.getEventDate())
                .eventStartTime(eventRequest.getEventStartTime())
                .eventEndTime(eventRequest.getEventEndTime())
                .eventLocation(eventRequest.getEventLocation())
                .contact(eventRequest.getContact())
                .createdAt(LocalDateTime.now())
                .build();
        eventRepository.save(eventEntity);
        return eventEntity.getId();
    }

    @Override
    public EventDto findById(long eventId) {
        eventRepository.incrementViewCountById(eventId);
        return eventRepository.findById(eventId)
                .map(eventMapper::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
    }

    @Override
    public List<EventDto> findByUserId(long userId) {
        return eventRepository.findByUserId(userId).stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findAllEvents() {
        return eventRepository.findAllWithPhotoUrls().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findByCategory(EventCategory category) {
        return eventRepository.findByCategory(category).stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findByEventDateBetween(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByEventDateBetween(startDate, endDate).stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findByEventDateBetweenAndCategory(LocalDate startDate,
                                                            LocalDate endDate,
                                                            EventCategory category) {
        return eventRepository.findByEventDateBetweenAndCategory(startDate, endDate, category)
                .stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long updateEvent(long eventId, EventRequest eventRequest,
                            MultipartFile photo,
                            MultipartFile[] photos) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
        uploadPhotos(event, photo, photos);
        event.setEventName(eventRequest.getEventName());
        event.setDescription(eventRequest.getDescription());
        event.setCategory(eventRequest.getCategory());
        event.setLanguage(eventRequest.getLanguage());
        event.setEventPrice(eventRequest.getEventPrice());
        event.setEventDate(eventRequest.getEventDate());
        event.setEventStartTime(eventRequest.getEventStartTime());
        event.setEventEndTime(eventRequest.getEventEndTime());
        event.setEventLocation(eventRequest.getEventLocation());
        event.setContact(eventRequest.getContact());
        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.save(event);
        return event.getId();
    }

    @Override
    @Transactional
    public void deleteEvent(long eventId) {
        reservationService.deleteByEventId(eventId);
        eventRepository.deleteById(eventId);
    }

    @Override
    @Transactional
    public void uploadPhotos(EventEntity event, MultipartFile mainPhoto, MultipartFile[] photos) {
        List<String> photoUrls = Arrays.stream(photos)
                .filter(photo -> !photo.isEmpty())
                .map(EventServiceImpl::apply)
                .collect(Collectors.toList());
        event.setMainPhotoUrl(apply(mainPhoto));
        event.setPhotoUrls(photoUrls);
        eventRepository.save(event);
        fileUtil.uploadFile(mainPhoto);
        fileUtil.uploadFiles(photos);
    }

    @Override
    public Long getViewCount(long eventId) {
        return eventRepository.findById(eventId)
                .map(EventEntity::getViewCount)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
    }

    @Override
    public List<EventDto> searchAll(String keyword) {
        return eventRepository.searchAll(keyword).stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findLikedEventsByUserId(long userId) {
        return eventRepository.findLikedEventsByUserId(userId).stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    //@Override
    //public byte[] downloadPhoto(String photoName) {
    //    return fileUtil.downloadFile(photoName);
    //}

    //@Override
    //public List<String> findPhotoNamesByEventId(long eventId) {
    //    return eventRepository.findById(eventId)
    //            .map(EventEntity::getPhotoUrls)
    //            .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
    //}

}