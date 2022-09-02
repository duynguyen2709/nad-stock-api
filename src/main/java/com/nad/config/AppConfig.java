package com.nad.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author duynguyen
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private StockConfig stock;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StockConfig {
        private int priceCacheTtl;
    }
}
