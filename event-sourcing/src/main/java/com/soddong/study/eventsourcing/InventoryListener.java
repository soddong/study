package com.soddong.study.eventsourcing;

import org.springframework.context.event.EventListener;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryListener {

    private final Map<String, Integer> inventory = new HashMap<>(Map.of("상품A", 10, "상품B", 20));

    @EventListener
    public void onOrderCreated(OrderCreatedEvent event) {
        inventory.computeIfPresent(event.product(), (k, v) -> v - 1);
        System.out.println("[재고 차감] " + event.product() + " → 남은 재고: " + inventory.get(event.product()));
    }

    @EventListener
    public void onOrderCancelled(OrderCancelledEvent event) {
        inventory.computeIfPresent(event.product(), (k, v) -> v + 1);
        System.out.println("[보상: 재고 복원] " + event.product() + " → 남은 재고: " + inventory.get(event.product()));
    }
}
