package twcrone.gitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class GitemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitemApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		var factory = new DefaultUriBuilderFactory();
		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
		return WebClient.builder()
				.uriBuilderFactory(factory)
				.build();
	}

}
