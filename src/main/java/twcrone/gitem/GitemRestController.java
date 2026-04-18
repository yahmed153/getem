package twcrone.gitem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GitemRestController {

    @GetMapping("/health")
    public Mono<String> getHealth() {
        return Mono.just("OK");
    }
}
