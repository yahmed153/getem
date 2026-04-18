package twcrone.gitem.integration;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import twcrone.gitem.api.GitemUser;
import twcrone.gitem.external.GithubRepo;
import twcrone.gitem.external.GithubUser;

import java.util.List;

@Component
public class GitHubToGitemTransformer {
    public Mono<GitemUser> from(Mono<GithubUser> githubUserMono, Mono<List<GithubRepo>> githubReposMono) {
        return Mono.empty();
    }
}
