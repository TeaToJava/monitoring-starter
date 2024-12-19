package ru.clevertec.monitoringstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.monitoringstarter.aop.PerformanceMonitorAspect;

@Configuration
@EnableConfigurationProperties(PerformanceMonitorProperties.class)
@ConditionalOnClass(PerformanceMonitorProperties.class)
@ConditionalOnProperty(prefix = "app.monitoring.logging", name = "enabled", havingValue = "true")
public class PerformanceMonitorAutoConfiguration {

    @Bean
    public PerformanceMonitorAspect performanceMonitorAspect(PerformanceMonitorProperties properties) {
        return new PerformanceMonitorAspect(properties);
    }

}
