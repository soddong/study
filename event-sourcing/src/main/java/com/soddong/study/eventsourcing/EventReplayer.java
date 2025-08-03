package com.soddong.study.eventsourcing;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventReplayer {

    private final EventStore eventStore; // JPA Repository
    private final Gson gson = new Gson(); // JSON 직렬화/역직렬화

    public void replay() {
        System.out.println("===== Event Replay 시작 =====");
        eventStore.findAll().forEach(storedEvent -> {
            try {
                Class<?> eventClass = Class.forName(storedEvent.getType());
                Object eventObj = gson.fromJson(storedEvent.getPayload(), eventClass);

                System.out.println("Replaying event: " + eventObj);

//                 publisher.publishEvent(eventObj);

            } catch (ClassNotFoundException e) {
                System.err.println("이벤트 클래스 로드 실패: " + storedEvent.getType());
            }
        });
        System.out.println("===== Event Replay 완료 =====");
    }
}

