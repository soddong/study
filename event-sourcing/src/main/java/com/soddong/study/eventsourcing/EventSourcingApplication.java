package com.soddong.study.eventsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

@SpringBootApplication
public class EventSourcingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EventSourcingApplication.class, args);

        OrderService orderService = context.getBean(OrderService.class);

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        orderService.createOrder(id1, "상품A");
        orderService.createOrder(id2, "상품B");
        orderService.cancelOrder(id1, "상품A");
        orderService.cancelOrder(id2, "상품B");

//        EventReplayer replayer = context.getBean(EventReplayer.class);
//        replayer.replay();
    }
}