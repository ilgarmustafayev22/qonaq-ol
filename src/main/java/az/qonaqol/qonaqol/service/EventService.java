package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.request.EventRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Long createEvent(EventRequest eventRequest, MultipartFile mainPhoto, MultipartFile[] photos);

    Long createEventTest(EventRequest eventRequest);

    EventDto findById(long eventId);

    List<EventDto> findAllEvents();

    EventDto findByCategory(EventCategory category);

    List<EventDto> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    List<EventDto> findByEventDateBetweenAndCategory(LocalDate startDate, LocalDate endDate, EventCategory category);

    Long updateEvent(long eventId, EventRequest eventRequest);

    void deleteEvent(long eventId);

    void uploadPhotos(long eventId, MultipartFile photo, MultipartFile[] photos);

    //byte[] downloadPhoto(String fileName);
    //List<String> findPhotoNamesByEventId(long eventId);

}
