package app.visualmusic.cover.port.input.spring.web.common;

import lombok.Value;

@Value(staticConstructor = "of")
public class ParameterConstraintViolation {
    String parameter;

    String message;
}
