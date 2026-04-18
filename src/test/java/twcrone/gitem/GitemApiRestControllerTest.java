package twcrone.gitem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.json.JsonMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
public class GitemApiRestControllerTest {

    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:7-alpine")
            .withExposedPorts(6379)
            .withCommand("redis-server");

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", redis::getFirstMappedPort);
        registry.add("spring.data.redis.password", () -> "mysecretpassword");
    }

    @Autowired
    private WebTestClient webTestClient;// = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Autowired
    private JsonMapper jsonMapper;


//    @Autowired
//    private GithubService githubService;
//
//    private GithubUser mockUser;
//    private List<GithubRepo> mockRepos;
//
//    @TestConfiguration
//    static class TestConfig {
//        @Bean
//        @Primary
//        public GithubService githubService() {
//            return Mockito.mock(GithubService.class);
//        }
//    }

//    @BeforeEach
//    void setUp() throws IOException {
//        // Load test data from resources
//        String userJson = Files.readString(Path.of("src/test/resources/user.json"));
//        String reposJson = Files.readString(Path.of("src/test/resources/repos.json"));
//
//        mockUser = jsonMapper.readValue(userJson, GithubUser.class);
//        mockRepos = jsonMapper.readValue(reposJson, jsonMapper.getTypeFactory().constructCollectionType(List.class, GithubRepo.class));
//
//        // Mock the GithubService
//        when(githubService.getUser(anyString())).thenReturn(Mono.just(mockUser));
//        when(githubService.getReposFor(anyString())).thenReturn(Mono.just(mockRepos));
//    }

    @Test
    void testGetUser() {
        webTestClient.get()
                .uri("/user/{userId}", "octocat")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.user_name").isEqualTo("octocat")
                .jsonPath("$.display_name").isEqualTo("The Octocat")
                .jsonPath("$.repos").isArray()
                .jsonPath("$.repos[0].name").exists();
    }
}
