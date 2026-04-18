package twcrone.gitem.integration;

import org.springframework.stereotype.Component;
import twcrone.gitem.api.GitemUser;
import twcrone.gitem.api.GitemRepo;
import twcrone.gitem.external.GithubUser;
import twcrone.gitem.external.GithubRepo;

import java.util.List;

@Component
public class GitHubToGitemTransformer {
    public GitemUser from(GithubUser user, List<GithubRepo> repos) {
        List<GitemRepo> gitemRepos = repos.stream()
            .map(repo -> new GitemRepo(repo.name(), repo.url()))
            .toList();

        return new GitemUser(
            user.login(),
            user.name(),
            user.avatarUrl(),
            user.location(),
            user.email(),
            user.url(),
            user.createdAt(),
            gitemRepos
        );
    }
}
