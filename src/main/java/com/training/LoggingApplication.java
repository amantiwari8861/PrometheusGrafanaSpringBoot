package com.training;

import com.training.service.*;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
	}

//    @Bean
//    CommandLineRunner testCounter(UserMetricsService metricsService) {
//        return args -> {
//            metricsService.recordLogin();
//            metricsService.recordLogin();
//            metricsService.recordLogin();
//
//            System.out.println("Login count = " + metricsService.getLoginCount());
//        };
//    }
//    @Bean
//    CommandLineRunner testGauge(
//            ActiveUserMetricsService service,
//            MeterRegistry registry) {
//
//        return args -> {
//
//            service.userConnected();
//            service.userConnected();
//            service.userConnected();
//
//            System.out.println(
//                    "Active users: " +
//                            registry.get("user.active.count")
//                                    .gauge()
//                                    .value()
//            );
//
//            service.userDisconnected();
//
//            System.out.println(
//                    "Active users: " +
//                            registry.get("user.active.count")
//                                    .gauge()
//                                    .value()
//            );
//        };
//    }


//    @Bean
//    CommandLineRunner testTimer(OrderMetricsService service) {
//
//        return args -> {
//
//            service.processOrder();
//            service.processOrder();
//            service.processOrder();
//
//            service.printMetrics();
//        };
//    }

//    @Bean
//    CommandLineRunner testDistributionSummary(OrderAmountMetricsService service) {
//
//        return args -> {
//
//            service.recordOrder(500);
//            service.recordOrder(1200);
//            service.recordOrder(800);
//            service.recordOrder(2000);
//            service.recordOrder(1500);
//
//            service.printMetrics();
//        };
//    }

    @Bean
    CommandLineRunner testTags(
            LoginMetricsService service,
            MeterRegistry registry) {

        return args -> {

            service.recordSuccess();
            service.recordSuccess();
            service.recordSuccess();

            service.recordFailure();

            double success = registry
                    .get("user.login.attempts")
                    .tag("status", "success")
                    .counter()
                    .count();

            double failure = registry
                    .get("user.login.attempts")
                    .tag("status", "failure")
                    .counter()
                    .count();

            System.out.println("Success: " + success);
            System.out.println("Failure: " + failure);
        };
    }

}
