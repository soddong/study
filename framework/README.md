# 사용법
```java
@AutoGet
public String getUser(String userId, boolean detailed) {
    // ...
}
```
1. `@AutoGet` 어노테이션 추가
2. Method를 기반으로 /user/{userId} 이라는 GET API를 만들고
3. userId, detailed 값을 자동으로 파라미터로 주입
4. 로그 출력

# 기본 규칙
* get prefix는 무시
* getUser(String userId) → /user/{userId}
* getUserProfile(String userId) → /user-profile/{userId}
* 파라미터 중 첫 번째가 식별자(ID)면, path variable로 변환
* 나머지는 쿼리 파라미터로 처리

# 디렉토리 설명
| 디렉토리                                  | 설명                                              |
| ------------------------------------- | ----------------------------------------------- |
| `annotation/`                         | `@AutoGet`, `@AutoPost` 등 어노테이션 정의              |
| `core/`                               | URI 생성, API 등록, 공통 유틸                           |
| `resolver/`                           | 파라미터 자동 바인딩 담당 (`HandlerMethodArgumentResolver`) |
| `logging/`                            | AOP 기반 로깅 처리                                    |
| `config/`                             | 스프링 자동 구성용 설정 클래스 (`@Configuration`)            |
| `resources/META-INF/spring.factories` | Spring Boot가 자동으로 이 프레임워크를 활성화하도록 함             |

# 동작 방식
1. @RestEndpoint가 붙은 클래스를 스캔
2. 그 안의 메서드에서 @AutoGet / @AutoPost 등 사용자 정의 어노테이션을 찾음
3. 메서드명과 파라미터를 기반으로 URI를 자동 생성 (/user/{userId} 등)
4. RequestMappingHandlerMapping을 통해 스프링에 직접 엔드포인트로 등록
5. AutoArgumentResolver가 파라미터를 자동으로 주입 (Path, Query, Body 등)
