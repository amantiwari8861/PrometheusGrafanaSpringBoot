package com.training.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OrderMetricsService {

    private final Timer orderTimer;

    public OrderMetricsService(MeterRegistry registry) {

        this.orderTimer = Timer.builder("order.processing.duration")
                .description("Time taken to process an order")
                .register(registry);
    }

    public void processOrder() {

        orderTimer.record(() -> {

            // Simulate order processing
            try {
                Thread.sleep((long) (Math.floor(Math.random()*10)*100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(
                        "Order processing interrupted", e
                );
            }

            System.out.println("Order processed");
        });
    }

    public void printMetrics() {

        System.out.println("Executions: " + orderTimer.count());

        System.out.println("Total Time: " +
                orderTimer.totalTime(TimeUnit.MILLISECONDS) + " ms");
        System.out.println("Average :"+orderTimer.totalTime(TimeUnit.MILLISECONDS)/orderTimer.count());
        System.out.println("Max Time: " +
                orderTimer.max(TimeUnit.MILLISECONDS) + " ms");
    }
}