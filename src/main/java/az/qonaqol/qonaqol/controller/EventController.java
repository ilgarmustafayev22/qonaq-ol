package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.dto.EventDto;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import az.qonaqol.qonaqol.model.request.EventRequest;
import az.qonaqol.qonaqol.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Event creation", description = "This method is used to create event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(path = "/create-event", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createEvent(@RequestBody MultipartFile file,
                                            @RequestBody MultipartFile[] files,
                                            EventRequest eventRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventRequest, file, files));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> findById(@Valid @PathVariable long eventId) {
        return ResponseEntity.ok(eventService.findById(eventId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventDto>> findByUserId(@Valid @PathVariable long userId) {
        return ResponseEntity.ok(eventService.findByUserId(userId));
    }

    @GetMapping("/all-events")
    public ResponseEntity<List<EventDto>> findAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<EventDto>> findByCategory(@Valid @PathVariable EventCategory category) {
        return ResponseEntity.ok(eventService.findByCategory(category));
    }

    @GetMapping("/date-between")
    public ResponseEntity<List<EventDto>> findByEventDateBetween(@RequestParam("startDate") LocalDate startDate,
                                                                 @RequestParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(eventService.findByEventDateBetween(startDate, endDate));
    }

    @GetMapping("/date-between-category")
    public ResponseEntity<List<EventDto>> findByEventDateBetweenAndCategory(@RequestParam("startDate") LocalDate startDate,
                                                                            @RequestParam("endDate") LocalDate endDate,
                                                                            @RequestParam("category") EventCategory category) {
        return ResponseEntity.ok(eventService.findByEventDateBetweenAndCategory(startDate, endDate, category));
    }

    @PutMapping(path = "/{eventId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> updateEvent(@PathVariable long eventId,
                                            @RequestBody MultipartFile file,
                                            @RequestBody MultipartFile[] files,
                                            EventRequest eventRequest) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, eventRequest, file, files));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@Valid @PathVariable long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/view-count/{eventId}")
    public ResponseEntity<Long> getViewCount(@Valid @PathVariable long eventId) {
        return ResponseEntity.ok(eventService.getViewCount(eventId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventDto>> searchAll(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(eventService.searchAll(keyword));
    }

    @GetMapping("/liked-events/{userId}")
    public ResponseEntity<List<EventDto>> findLikedEventsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(eventService.findLikedEventsByUserId(userId));
    }

    // @GetMapping("/photosNames/{eventId}")
    // public ResponseEntity<List<String>> getPhotosNames(@Valid @PathVariable long eventId) {
    //     return ResponseEntity.ok(eventService.findPhotoNamesByEventId(eventId));
    // }

    // @GetMapping("/download-photo/{photoName}")
    // public ResponseEntity<byte[]> downloadFile(@PathVariable("photoName") String fileName) {
    //     byte[] imageData = eventService.downloadPhoto(fileName);
    //     return ResponseEntity.status(HttpStatus.OK)
    //             .contentType(MediaType.valueOf("image/png"))
    //             .body(imageData);
    // }

    // @GetMapping("/download/{fileName}")
    // public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
    //     byte[] data = fileUtil.downloadFile(fileName);
    //     ByteArrayResource resource = new ByteArrayResource(data);
    //     return ResponseEntity
    //             .ok()
    //             .contentLength(data.length)
    //             .header("Content-type", "application/octet-stream")
    //             .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
    //             .body(resource);
    // }

    // @PostMapping(path = "/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<Void> uploadPhotos(@RequestBody MultipartFile[] files, @RequestParam("eventId") long eventId) {
    //     eventService.uploadPhotos(eventId, files);
    //     return ResponseEntity.ok().build();
    // }

}
