package com.soddong.study.eventsourcing;

import com.google.gson.Gson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventStore extends JpaRepository<StoredEvent, UUID> {

    default void saveEvent(Object event) {
        String type = event.getClass().getName();
        String payload = new Gson().toJson(event);
        save(new StoredEvent(type, payload));
    }
}