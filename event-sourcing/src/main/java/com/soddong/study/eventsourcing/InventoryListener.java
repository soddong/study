package com.soddong.study.eventsourcing;

import org.springframework.context.event.EventListener;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    @EventListener
    public void handle(OrderCreatedEvent event) {
        System.out.println("재고 차감: " + event.product());
    }
}
