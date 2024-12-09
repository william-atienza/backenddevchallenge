package com.limepay.backenddevchallenge.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStore {
    @Value("${limepay.cache.expiry}")
    private int CACHE_EXPIRY;

    @Bean
    public Caffeine caffeineConfig(){
        return Caffeine.newBuilder().expireAfterWrite(CACHE_EXPIRY, TimeUnit.MINUTES);
    }
    @Bean
    public CacheManager cacheManager(Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("directorsCache");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
