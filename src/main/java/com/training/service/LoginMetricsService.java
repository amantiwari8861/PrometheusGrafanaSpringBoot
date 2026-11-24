package com.training.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class LoginMetricsService {

    private final Counter successCounter;
    private final Counter failureCounter;

    public LoginMetricsService(MeterRegistry registry) {

        this.successCounter = Counter.builder("user.login.attempts")
                .description("Total login attempts")
                .tag("status", "success")
                .register(registry);

        this.failureCounter = Counter.builder("user.login.attempts")
                .description("Total login attempts")
                .tag("status", "failure")
                .register(registry);
    }

    public void recordSuccess() {
        successCounter.increment();
    }

    public void recordFailure() {
        failureCounter.increment();
    }
}