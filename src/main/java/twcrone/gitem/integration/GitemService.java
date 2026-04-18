package twcrone.gitem.integration;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import twcrone.gitem.api.GitemRepo;
import twcrone.gitem.api.GitemUser;
import twcrone.gitem.external.GithubService;

import java.util.List;

@Service
public class GitemService {

    private final GithubService githubService;
    private final GitHubToGitemTransformer transformer;

    public GitemService(GithubService githubService, GitHubToGitemTransformer transformer) {
        this.githubService = githubService;
        this.transformer = transformer;
    }

    public Mono<GitemUser> getem(String userId) {
        return githubService.getUser(userId)
                .map(user -> transformer.from(user, List.of()));
    }
}
