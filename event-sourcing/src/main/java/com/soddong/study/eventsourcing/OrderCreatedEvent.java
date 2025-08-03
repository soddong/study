package com.soddong.study.eventsourcing;

import java.util.UUID;

public record OrderCreatedEvent(UUID orderId, String product) { }
