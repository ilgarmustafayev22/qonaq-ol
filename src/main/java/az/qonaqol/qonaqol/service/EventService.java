package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.request.EventRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Long createEvent(EventRequest eventRequest, MultipartFile mainPhoto, MultipartFile[] photos);

    EventDto findById(long eventId);

    List<EventDto> findByUserId(long userId);

    List<EventDto> findAllEvents();

    List<EventDto> findByCategory(EventCategory category);

    List<EventDto> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    List<EventDto> findByEventDateBetweenAndCategory(LocalDate startDate, LocalDate endDate, EventCategory category);

    Long updateEvent(long eventId, EventRequest eventRequest, MultipartFile photo, MultipartFile[] photos);

    void deleteEvent(long eventId);

    void uploadPhotos(EventEntity event, MultipartFile photo, MultipartFile[] photos);

    Long getViewCount(long eventId);

    List<EventDto> searchAll(String keyword);

    List<EventDto> findLikedEventsByUserId(long userId);

    //byte[] downloadPhoto(String fileName);
    //List<String> findPhotoNamesByEventId(long eventId);

}
