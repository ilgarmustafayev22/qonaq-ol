package az.qonaqol.qonaqol.controller;

import az.qonaqol.qonaqol.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like-event")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{eventId}/{userId}")
    public ResponseEntity<Void> toggleLike(@PathVariable long eventId,
                                           @PathVariable long userId) {
        likeService.toggleLike(eventId, userId);
        return ResponseEntity.ok().build();
    }

}
