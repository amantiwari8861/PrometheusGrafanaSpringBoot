package com.training.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class UserMetricsService {

    private final Counter loginCounter;

    public UserMetricsService(MeterRegistry meterRegistry) {
        this.loginCounter = Counter.builder("user.login.count")
                .description("Total number of user login attempts")
                .register(meterRegistry);
    }

    public void recordLogin() {
        loginCounter.increment();
    }

    public double getLoginCount() {
        return loginCounter.count();
    }
}