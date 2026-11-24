package com.training.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

//    @Bean
//    public MeterRegistry meterRegistry() {
//        return new SimpleMeterRegistry(); // Creates an in-memory metrics registry.
//    }
//    @Bean
//    public MeterRegistry meterRegistry() {
//        MeterRegistry registry = new SimpleMeterRegistry();
//        System.out.println("Micrometer Registry Created: " + registry);
//        return registry;
//    }
}