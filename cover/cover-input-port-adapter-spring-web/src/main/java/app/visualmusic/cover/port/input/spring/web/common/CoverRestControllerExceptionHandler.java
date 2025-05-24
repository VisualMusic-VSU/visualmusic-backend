package app.visualmusic.cover.port.input.spring.web.common;

import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import app.visualmusic.cover.shared.exception.ReferenceItemNotFoundException;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoverRestControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(UserNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ReferenceItemNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(ReferenceItemNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(GroupNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CoverNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(CoverNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<ProblemDetail> buildResponseEntity(HttpStatus httpStatus, String exMessage) {
        return ResponseEntity
                .of(ProblemDetail.forStatusAndDetail(httpStatus, exMessage))
                .build();
    }
}
