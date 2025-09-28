# Spring 만들어보기
* 스프링 프레임워크 Core(IoC/DI)를 직접 구현하면서 이해하는 과정을 단계별로 정리한 것  
* 각 Step마다 기능을 확장해가며, 실제 스프링의 동작 원리를 흉내내며 이해하는 것을 목표로 함

## TODO (로드맵)

- [x] Step 1: Config 기반 IoC 컨테이너 (@Configuration + @Bean)
- [x] Step 2: @Autowired 지원 (자동 의존성 주입)
- [ ] Step 3: BeanDefinition 도입
- [ ] Step 3: Bean 라이프사이클 콜백 (@PostConstruct, @PreDestroy)
- [ ] Step 4: Component 스캔 (@ComponentScan)
- [ ] Step 5: Bean 스코프 (singleton, prototype 등)


---

## Step 1: Config 기반 IoC 컨테이너

### 개요
* 스프링 초창기에는 XML 대신 자바 설정 클래스(@Configuration)과 @Bean 메서드를 이용해 빈을 등록했음
* 따라서 해당 방식을 따라하여 가장 단순한 IoC 컨테이너를 만드는것을 목표로 함

### 구현 내용

#### 1. BeanFactory
- Bean을 등록하고 조회할 수 있는 가장 기본적인 컨테이너 인터페이스
- 내부에 `Map<Class<?>, Object>`를 두고, 타입을 키로 하여 객체를 관리하도록 구현
- -> 이후 확장) 현재는 같은 타입의 Bean을 등록못함. 빈 이름을 키로 사용하도록 수정 필요
- -> BeanDefinition 빈을 어떻게 정의할것인지를 나타내는 요 클래스까지

#### 2. ConfigurationProcessor
- 설정 클래스(@Configuration)를 읽어 @Bean 메서드를 실행함
- 실행 결과 객체를 BeanFactory에 등록하는 역할

#### 3. ApplicationContext
- 외부에서 BeanFactory를 직접 쓰지 않고, `ApplicationContext`를 통해 Bean을 조회
- 내부적으로 ConfigurationProcessor를 실행하여 BeanFactory에 Bean들을 등록한 뒤, `getBean()`으로 조회 가능하게 만듦

---

### 코드 (사용시)

```java
@Configuration
public class AppConfig {
    @Bean
    public SoddongRepository soddongRepository() {
        return new SoddongRepository();
    }

    @Bean
    public SoddongService soddongService() {
        return new SoddongService(soddongRepository());
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        SoddongService service = context.getBean(SoddongService.class);
        service.process(); // 결과: Soddong Result!
    }
}
```

## Step 2: @Autowired 지원 (자동 의존성 주입)

### 개요
* Step 1에서는 설정 클래스 내부에서 직접 의존성을 연결해야 했음
* 아래 코드와 같음
  * service는 필드로 repository를 가지므로, 명시적으로 생성시 repository를 넣어줘야 했음
  * 하지만 기능 추가함으로써, BeanFactory가 @Autowired 어노테이션을 보고 해당 필드 타입에 맞는 Bean을 찾아 자동으로 넣어줌
```java
    // before
    @Bean
    public SoddongRepository soddongRepository() {
        return new SoddongService(soddongRepository());
    }
        
    // after
    @Bean
    public SoddongRepository soddongRepository() {
        return new SoddongService();
    }
```

### 1. @Autowired 어노테이션 추가
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
```

### 2. DefaultBeanFactory 확장
* Bean 등록 시 performInjection() 실행
* @Autowired가 붙은 필드를 찾아 타입에 맞는 Bean을 자동으로 주입
```java
private void performInjection(Object bean) {
    for (Field field : bean.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(Autowired.class)) {
            Object dependency = getBean(field.getType());
            if (dependency == null) {
                throw new IllegalStateException("주입할 빈을 찾을 수 없음: " + field.getType());
            }
            try {
                field.setAccessible(true);
                field.set(bean, dependency);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("의존성 주입 실패: " + field, e);
            }
        }
    }
}
```
* 동작 방식: 필드 순회 -> @Autowired 확인 -> 해당 필드의 타입 기반 Bean 조회 -> 주입
* -> 현재 구조는 Bean이 등록되는 즉시 주입을 시도함.
* -> 따라서 의존 대상이 먼저 등록되지 않았다면 getBean()에서 null이 반환되거나 예외 발생
* -> BeanDefinition 필요...!