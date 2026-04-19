package twcrone.gitem.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class RestConrollerAdvice {

    @ExceptionHandler(WebClientResponseException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleWebClientExceptions(WebClientResponseException webclientException) {
        if(HttpStatus.NOT_FOUND.equals(webclientException.getStatusCode())) {
            var error = new ErrorResponse("NOT_FOUND", "Github does not have that user");
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
        }
        // here might be a good place to hand different error codes from GitHub API
        // for one, if we get a user response but repo call fails, maybe it's okay to return a PARTIAL
        ErrorResponse error = new ErrorResponse("SERVICE_UNAVAILABLE", "Github API is not available at this time");
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error));
    }

    // for now, by default, let's just blame GitHub ;-)
    // obviously we would want more extensive handling of various kinds of issues
    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGeneralError(RuntimeException runtimeException) {
        ErrorResponse error = new ErrorResponse("SERVICE_UNAVAILABLE", "Github API is not available at this time");
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error));
    }

    public record ErrorResponse(String code, String message){};
}
