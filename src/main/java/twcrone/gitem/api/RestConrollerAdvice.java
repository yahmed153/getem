package twcrone.gitem.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class RestConrollerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGeneralError(RuntimeException runtimeException) {
        ErrorResponse error = new ErrorResponse("SERVICE_UNAVAILABLE", "Github API is not available at this time");
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error));
    }

    public record ErrorResponse(String code, String message){};
}
