package com.soddong.study.eventsourcing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class StoredEvent {
    @Id @GeneratedValue
    private UUID id;
    private String type;
    @Lob
    private String payload;
    private LocalDateTime createdAt;

    @Builder
    public StoredEvent(String type, String payload) {
        this.type = type;
        this.payload = payload;
        this.createdAt = LocalDateTime.now();
    }

}
