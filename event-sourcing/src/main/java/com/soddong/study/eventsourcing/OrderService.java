package com.soddong.study.eventsourcing;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final EventStore eventStore;
    private final ApplicationEventPublisher publisher;

    public void createOrder(UUID orderId, String product) {
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, product);
        eventStore.saveEvent(event);
        publisher.publishEvent(event);
    }

    public void cancelOrder(UUID orderId, String product) {
        OrderCancelledEvent event = new OrderCancelledEvent(orderId, product);
        publisher.publishEvent(event);
    }
}