package com.soddong.study.eventsourcing;

import java.util.UUID;

public record OrderCancelledEvent(UUID orderId, String product) {}

