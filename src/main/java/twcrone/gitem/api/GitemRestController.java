package twcrone.gitem.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import twcrone.gitem.GitemService;

@RestController
public class GitemRestController {

    private final GitemService gitemService;

    public GitemRestController(GitemService gitemService) {
        this.gitemService = gitemService;
    }

    @GetMapping("/health")
    public Mono<String> getHealth() {
        return Mono.just("OK");
    }

    @GetMapping("/user/{userId}")
    public Mono<GitemUser> getUser(@PathVariable String userId) {
        return gitemService.getem(userId);
    }
}