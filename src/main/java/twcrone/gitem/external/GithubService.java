package twcrone.gitem.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${github.api.base-url}")
    private String baseUrl;

    public GithubService(JsonMapper jsonMapper, WebClient webClient) {
        this.jsonMapper = jsonMapper;
        this.webClient = webClient;
    }

    /*
        endpoints:
      user: /users/{userId}
      repos: /users/{userId}/repos

     */
    public Mono<GithubUser> getUser(String userId) {
        return webClient.get()
                .uri("{baseUrl}/users/{userId}", this.baseUrl, userId)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> jsonMapper.readValue(body, GithubUser.class));
    }

    public Mono<List<GithubRepo>> getReposFor(String userId) {
        log.info("Calling Github...");
        return webClient.get()
                .uri("{baseUrl}/users/{userId}/repos", this.baseUrl,  userId)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> jsonMapper.readValue(body, jsonMapper.getTypeFactory().constructCollectionType(List.class, GithubRepo.class)));
    }
}
