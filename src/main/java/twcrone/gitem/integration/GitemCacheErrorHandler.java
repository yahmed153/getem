package twcrone.gitem.integration;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

import static reactor.netty.http.HttpConnectionLiveness.log;

@Component
public class GitemCacheErrorHandler implements CacheErrorHandler {
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("Cache GET failed for key {}: {}", key, exception.getMessage());
        // Do nothing to let the method execute normally
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log.error("Cache PUT failed for key {}: {}", key, exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("Cache EVICT failed for key {}: {}", key, exception.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("Cache CLEAR failed: {}", exception.getMessage());
    }
}
