package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.model.request.CreateEventRequest;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    List<String> findPhotoUrlsByEventId(long eventId);

    void createEvent(CreateEventRequest eventRequest, MultipartFile[] files);

}
