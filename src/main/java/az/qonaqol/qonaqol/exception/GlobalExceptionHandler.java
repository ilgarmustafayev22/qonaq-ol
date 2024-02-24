package az.qonaqol.qonaqol.exception;

import az.qonaqol.qonaqol.model.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorDto<UserNotFoundException>> handleUserNotFoundException(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto<>(404001,
                        ex.getMessage(),
                        UserNotFoundException.class,
                        LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDto<UserAlreadyExistsException>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto<>(404002,
                        ex.getMessage(),
                        UserAlreadyExistsException.class,
                        LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto<IllegalArgumentException>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto<>(404003,
                        ex.getMessage(),
                        IllegalArgumentException.class,
                        LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PhotoNotFoundException.class)
    public ResponseEntity<ErrorDto<PhotoNotFoundException>> handleIllegalArgumentException(PhotoNotFoundException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto<>(404004,
                        ex.getMessage(),
                        PhotoNotFoundException.class,
                        LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<ErrorDto<EventNotFoundException>> handleIllegalArgumentException(EventNotFoundException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto<>(404005,
                        ex.getMessage(),
                        EventNotFoundException.class,
                        LocalDateTime.now()));
    }

}
