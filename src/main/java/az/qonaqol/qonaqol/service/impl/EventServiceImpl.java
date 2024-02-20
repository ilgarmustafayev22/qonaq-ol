package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.dao.entity.EventPhotoEntity;
import az.qonaqol.qonaqol.dao.repository.EventPhotoRepository;
import az.qonaqol.qonaqol.dao.repository.EventRepository;
import az.qonaqol.qonaqol.model.request.CreateEventRequest;
import az.qonaqol.qonaqol.service.EventService;
import az.qonaqol.qonaqol.util.FileUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final FileUtil fileUtil;

    private final EventRepository eventRepository;

    @Override
    public void createEvent(CreateEventRequest eventRequest, MultipartFile[] files) {
        List<String> imageUrls = Arrays.stream(files)
                .filter(file -> !file.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toCollection(ArrayList::new));

        eventRepository.save(EventEntity.builder()
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
                .maxParticipants(eventRequest.getMaxParticipants())
                .photoUrls(imageUrls)

                .build());
        fileUtil.uploadFile(files);
    }

    @Override
    public List<String> findPhotoUrlsByEventId(long eventId) {
        return eventRepository.findById(eventId).stream()
                .map(EventEntity::getPhotoUrls)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}