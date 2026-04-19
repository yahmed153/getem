package twcrone.gitem.integration;

import org.springframework.stereotype.Component;
import twcrone.gitem.api.GitemUser;
import twcrone.gitem.api.GitemRepo;
import twcrone.gitem.external.GithubUser;
import twcrone.gitem.external.GithubRepo;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static reactor.netty.http.HttpConnectionLiveness.log;

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
            dateFrom(user.createdAt()),
            gitemRepos
        );
    }

    private String dateFrom(String gitHubDateString) {
        String gitemFormattedDate = null;

        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(gitHubDateString);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
            gitemFormattedDate = dateTime.format(formatter);
        } catch (Exception e) {
            log.warn("Bad GitHub date format, return null: [{}]", gitHubDateString);
        }

        return gitemFormattedDate;
    }
}
