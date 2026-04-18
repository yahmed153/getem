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
    }

    @Autowired
    private WebTestClient webTestClient;// = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

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
