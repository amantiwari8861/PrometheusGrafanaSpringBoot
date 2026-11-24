package com.training.service;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class OrderAmountMetricsService {

    private final DistributionSummary orderAmountSummary;

    public OrderAmountMetricsService(MeterRegistry registry) {

//        this.orderAmountSummary =
//                DistributionSummary.builder("order.amount")
//                        .description("Distribution of order amounts")
//                        .baseUnit("rupees")
//                        .register(registry);
        this.orderAmountSummary =
                DistributionSummary.builder("order.amount")
                        .description("Distribution of order amounts")
                        .baseUnit("rupees")
                        .publishPercentiles(0.5, 0.95, 0.99)
                        .serviceLevelObjectives(
                                500,
                                1000,
                                2000,
                                5000
                        )
                        .register(registry);
    }

    public void recordOrder(double amount) {

        if (!Double.isFinite(amount) || amount < 0) {
            throw new IllegalArgumentException(
                    "Order amount must be finite and non-negative"
            );
        }

        orderAmountSummary.record(amount);
    }

    public void printMetrics() {

        System.out.println(
                "Count: " + orderAmountSummary.count()
        );

        System.out.println(
                "Total Amount: " + orderAmountSummary.totalAmount()
        );

        System.out.println(
                "Mean: " + orderAmountSummary.mean()
        );

        System.out.println(
                "Max: " + orderAmountSummary.max()
        );
    }
}