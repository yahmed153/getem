package twcrone.gitem.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Service
public class GithubService {

    private static final Logger log = LoggerFactory.getLogger(GithubService.class);
    private final JsonMapper jsonMapper;
    private final WebClient webClient;

    public GithubService(JsonMapper jsonMapper, WebClient webClient) {
        this.jsonMapper = jsonMapper;
        this.webClient = webClient;
    }

    public Mono<GithubUser> getUser(String userId) {
        return webClient.get()
                .uri("https://api.github.com/users/{userId}", userId)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> jsonMapper.readValue(body, GithubUser.class));
    }

    public Mono<List<GithubRepo>> getReposFor(String userId) {
        log.info("Calling Github...");
        return webClient.get()
                .uri("https://api.github.com/users/{userId}/repos", userId)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> jsonMapper.readValue(body, jsonMapper.getTypeFactory().constructCollectionType(List.class, GithubRepo.class)));
    }
}
