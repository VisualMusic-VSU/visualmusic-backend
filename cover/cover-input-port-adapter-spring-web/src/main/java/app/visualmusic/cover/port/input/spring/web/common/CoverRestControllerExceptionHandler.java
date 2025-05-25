package app.visualmusic.cover.port.input.spring.web.common;

import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import app.visualmusic.cover.shared.exception.ReferenceItemNotFoundException;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class CoverRestControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handle(ConstraintViolationException ex) {
        List<ParameterConstraintViolation> violations = ex.getConstraintViolations().stream()
                .map(e ->
                        ParameterConstraintViolation.of(extractSimplePropertyName(e.getPropertyPath()), e.getMessage())
                )
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("violations", violations);

        return ResponseEntity
                .of(problemDetail)
                .build();
    }

    private String extractSimplePropertyName(Path propertyPath) {
        String name = null;
        for (Path.Node node : propertyPath) {
            name = node.getName();
        }
        return name != null
                ? name
                : propertyPath.toString();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handle(MethodArgumentTypeMismatchException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Invalid path parameter");
    }

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
