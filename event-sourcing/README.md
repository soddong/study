# Event Sourcing
## 개념
Event Sourcing 은 애플리케이션 상태를 이벤트의 흐름으로 저장 -> 관리하는 아키텍처 패턴이다.
모든 상태 변화는 이벤트로 기록되고, 이벤트들을 순차적으로 replay하여 복원할 수 있다.

## 핵심 요소
### Event
* 도메인에서 이미 발생한 사실을 나타내는 불변 객체
* 상태 변화를 기록하고, 다른 모듈에 전달하기 위함 (후속 동작의 트리거 역할)
```text
public record OrderCreatedEvent(UUID orderId, String product) {}
```

### Event Store
* 이벤트를 순차적으로 저장하는 저장소 역할

### Publish
* 상태 변화 발생시 이벤트 발행하고 저장소에 기록
* Spring 에서는 `ApplicationEventPublisher` 인터페이스를 이용
```java
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
```

### Listener
* 이벤트를 수집하고 상태 변화를 복원하는 모듈 역할
* Spring 에서는 `ApplicationListener` 인터페이스를 이용
```java
public interface ApplicationListener<E extends ApplicationEvent> {
    void onApplicationEvent(E event);
}
```

### 이벤트 재생(Replay)
- 저장소에 기록된 이벤트들을 순차적으로 읽어 상태를 재구성
- 장애 복구, 새로운 Projection 생성 시 유용

## 결과
```bash
2025-08-03T21:47:18.671+09:00  INFO 10158 --- [event-sourcing] [  restartedMain] c.s.s.e.EventSourcingApplication         : Started EventSourcingApplication in 1.439 seconds (process running for 1.652)
2025-08-03T21:47:18.697+09:00 DEBUG 10158 --- [event-sourcing] [  restartedMain] org.hibernate.SQL                        : 
    insert 
    into
        stored_event
        (created_at, payload, type, id) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        stored_event
        (created_at, payload, type, id) 
    values
        (?, ?, ?, ?)
재고 차감: 상품A

```
