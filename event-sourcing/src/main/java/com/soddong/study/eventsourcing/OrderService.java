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

    public OrderService(EventStore eventStore, ApplicationEventPublisher publisher) {
        this.eventStore = eventStore;
        this.publisher = publisher;
    }

    public void createOrder(String product) {
        OrderCreatedEvent event = new OrderCreatedEvent(UUID.randomUUID(), product);
        eventStore.saveEvent(event);
        publisher.publishEvent(event);
    }
}
