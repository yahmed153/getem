package twcrone.gitem.integration;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class GitemCacheConfig implements CachingConfigurer {

    @Override
    public CacheErrorHandler errorHandler() {
        // Return your custom implementation or the built-in LoggingCacheErrorHandler
        return new GitemCacheErrorHandler();
    }
}