package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.model.request.CreateEventRequest;
import az.qonaqol.qonaqol.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
public class EventController {

    private final EventService eventService;

    // @PostMapping("/upload/{eventId}")
    // public ResponseEntity<Void> uploadFile(@PathVariable long eventId, @RequestParam("files") MultipartFile[] files) {
    //     eventService.uploadFile(eventId, files);
    //     return ResponseEntity.ok().build();
    // }
    @Operation(summary = "Event creation", description = "Creating event with photos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(name = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEvent(@RequestPart MultipartFile[] files,
                                             @Valid @Parameter(name = "eventRequest") CreateEventRequest eventRequest) {
        eventService.createEvent(eventRequest, files);
        return ResponseEntity.ok().build();
    }
    // @GetMapping("/download/{photoName}")
    // public ResponseEntity<byte[]> downloadFile(@PathVariable("photoName") String fileName) {
    //     byte[] imageData = eventService.downloadFile(fileName);
    //     return ResponseEntity.status(HttpStatus.OK)
    //             .contentType(MediaType.valueOf("image/png"))
    //             .body(imageData);
    // }

    @GetMapping("/urls/{eventId}")
    public ResponseEntity<List<String>> getUrls(@PathVariable("eventId") long eventId) {
        return ResponseEntity.ok(eventService.findPhotoUrlsByEventId(eventId));
    }

}
