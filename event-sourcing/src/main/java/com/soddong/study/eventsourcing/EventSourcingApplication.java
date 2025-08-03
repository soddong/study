package com.soddong.study.eventsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EventSourcingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EventSourcingApplication.class, args);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.createOrder("상품A");
    }
}