package app.visualmusic.auth.port.input.spring.web.common;

import lombok.Value;

@Value(staticConstructor = "of")
public class FieldConstraintViolation {
    String field;

    String message;
}
