package com.training.service;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ActiveUserMetricsService {

    private final AtomicInteger activeUsers = new AtomicInteger(0);

    public ActiveUserMetricsService(MeterRegistry registry) {

        Gauge.builder("user.active.count", activeUsers,AtomicInteger::get)
                .description("Currently active users")
                .register(registry);
    }

    public void userConnected() {
        activeUsers.incrementAndGet();
    }

    public void userDisconnected() {
        activeUsers.updateAndGet(
                count -> Math.max(0, count - 1)
        );
    }

    public int getActiveUsers() {
        return activeUsers.get();
    }
}