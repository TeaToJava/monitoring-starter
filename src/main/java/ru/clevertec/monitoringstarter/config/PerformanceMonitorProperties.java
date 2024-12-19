package ru.clevertec.monitoringstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.monitoring.logging")
public class PerformanceMonitorProperties {
    private boolean enabled;
    private int minLoggingInterval;

    @PostConstruct
    public void init() {
        log.info("Logging monitoring enabled: {}", enabled);
    }
}
