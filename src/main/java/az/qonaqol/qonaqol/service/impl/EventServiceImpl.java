package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.EventRepository;
import az.qonaqol.qonaqol.exception.EventNotFoundException;
import az.qonaqol.qonaqol.mapper.EventMapper;
import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.request.EventRequest;
import az.qonaqol.qonaqol.service.EventService;
import az.qonaqol.qonaqol.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    public static final String PHOTO_URL = "https://qonaqol.s3.eu-north-1.amazonaws.com/";
    private final FileUtil fileUtil;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    private static String apply(MultipartFile file) {
        return PHOTO_URL + file.getOriginalFilename();
    }

    @Override
    @Transactional
    public Long createEvent(EventRequest eventRequest) {
        EventEntity eventEntity = eventMapper.toEntity(eventRequest);
        eventRepository.save(eventEntity);
        return eventEntity.getId();
    }

    @Override
    public EventDto findById(long eventId) {
        return eventRepository.findById(eventId)
                .map(eventMapper::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
    }

    @Override
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto findByCategory(EventCategory category) {
        return eventRepository.findByCategory(category)
                .map(eventMapper::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found with category: " + category));
    }

    @Override
    @Transactional
    public Long updateEvent(long eventId, EventRequest eventRequest) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
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
        event.setMaxParticipants(eventRequest.getMaxParticipants());
        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.save(event);
        return event.getId();
    }

    @Override
    @Transactional
    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    @Transactional
    public void uploadPhotos(long eventId, MultipartFile[] files) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
        List<String> photoUrls = Arrays.stream(files)
                .filter(file -> !file.isEmpty())
                .map(EventServiceImpl::apply)
                .collect(Collectors.toList());
        event.setPhotoUrls(photoUrls);
        eventRepository.save(event);
        fileUtil.uploadFiles(files);
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