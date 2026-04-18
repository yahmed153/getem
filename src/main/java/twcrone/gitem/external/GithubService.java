package twcrone.gitem.external;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GithubService {
    public Mono<GithubUser> getUser(String userId) {
        return Mono.empty();
    }

    public Mono<List<GithubRepo>> getReposFor(String userId) {
        return Mono.empty();
    }
}
