package twcrone.gitem.integration;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import twcrone.gitem.api.GitemUser;
import twcrone.gitem.external.GithubService;

@Service
public class GitemService {

    private final GithubService githubService;
    private final GitHubToGitemTransformer transformer;

    public GitemService(GithubService githubService, GitHubToGitemTransformer transformer) {
        this.githubService = githubService;
        this.transformer = transformer;
    }

    public Mono<GitemUser> gitem(String userId) {
        return Mono.zip(
                githubService.getUser(userId),
                githubService.getReposFor(userId)
        ).map(tuple -> transformer.from(tuple.getT1(), tuple.getT2()));
    }
}
