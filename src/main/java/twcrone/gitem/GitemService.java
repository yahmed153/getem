package twcrone.gitem;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GitemService {

    public Mono<GitemUser> getem(String userId) {
        return Mono.just(new GitemUser(
                userId,
                "Bob Smith",
                "https://avatars.githubusercontent.com/u/123456?v=4",
                "San Francisco",
                "bob@example.com",
                "https://api.github.com/users/bob",
                "Tue, 25 Jan 2011 18:44:36 GMT",
                java.util.List.of(
                        new Repo("repo-1", "https://api.github.com/repos/bob/repo-1"),
                        new Repo("repo-2", "https://api.github.com/repos/bob/repo-2")
                )
        ));
    }
}
