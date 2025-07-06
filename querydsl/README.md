# QueryDsl 이란?
* Java 기반의 타입 안전한 쿼리(Query) 생성 라이브러리
* JPA(Hibernate), SQL, MongoDB 등에서 컴파일 타임에 오류를 잡을 수 있는 쿼리 작성을 가능하게 해주는 도구

# 기본 설정
### build.gradle.kts
* (플러그인) QueryDSL 코드 생성 플러그인 (Q 클래스 생성을 위해 필요) 추가
```kotlin
plugins {
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}
```

* (의존성) QueryDSL JPA 모듈 (JPA용 DSL 기능 사용을 위해) 추가
```kotlin
implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
```

* (의존성) 코드를 자동 생성을 위한 annotation processor 추가
```kotlin
// Q 클래스 생성을 위한 APT(Annotation Processor) 모듈
annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")

// @Entity, @Id 등의 JPA 어노테이션을 QueryDSL이 인식할 수 있도록 해주는 의존성
annotationProcessor("jakarta.persistence:jakarta.persistence-api:3.1.0")

// @Generated 등 Jakarta 공통 어노테이션 인식을 위한 의존성 (빌드 중 오류 방지)
annotationProcessor("jakarta.annotation:jakarta.annotation-api:2.1.1")
```

* (빌드설정) QueryDSL Q 클래스 생성 및 빌드에 반영하기 위한 설정
```kotlin
// QueryDSL Q 클래스가 생성될 경로 지정
val querydslDir = file("build/generated/querydsl")

// 생성된 Q 클래스를 컴파일 소스에 포함
sourceSets["main"].java {
    srcDir(querydslDir)
}

// Java 컴파일러가 Q 클래스 생성 결과물을 저장할 위치 설정
tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(querydslDir)
}

// build 시 Q 클래스가 항상 새로 생성되도록 설정
tasks.named("compileJava") {
    dependsOn("clean")
}
```

### JPAQueryFactory Bean 등록
* QueryDSL에서 쿼리를 실행을 위해 필요.

```java
@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
```

# 사용법
### JPA Repository Custom Implementation (QueryDsl + JPA) 
1. 커스텀할 메서드를 포함하는 인터페이스 추가
```java
public interface ContractJpaRepositoryCustom {
    List<ContractJpaEntity> searchByCondition(ContractQuery query);
}
```
2. 인터페이스 구현 
```java
    @Override
    public List<ContractJpaEntity> searchByCondition(ContractQuery query) {
        QContractJpaEntity c = QContractJpaEntity.contractJpaEntity;

        return queryFactory.selectFrom(c)
                .where(
                        statusEq(query),
                        startDateRange(query)
                )
                .fetch();
    }
```
3. JpaRepository 상속에 추가
```java
public interface ContractJpaRepository extends JpaRepository<ContractJpaEntity, Long>,
        ContractJpaReposi혀toryCustom {
}
```

### 동적 조건(where 절) 을 구성 방식

#### 1) BooleanExpression 방식
* 조건마다 null 체크 함수 분리
* where() 절에 null 넘기면 무시됨 
```java
   @Override
    public List<ContractJpaEntity> searchByCondition(ContractQuery query) {
        QContractJpaEntity c = QContractJpaEntity.contractJpaEntity;

        return queryFactory.selectFrom(c)
                .where(
                        statusEq(query),
                        startDateRange(query)
                )
                .fetch();
    }

    private BooleanExpression statusEq(ContractQuery query) {
        return query.status() != null ? QContractJpaEntity.contractJpaEntity.status.eq(query.status()) : null;
    }

    private BooleanExpression startDateRange(ContractQuery query) {
        LocalDate from = query.startDateFrom();
        LocalDate to = query.startDateTo();
        QContractJpaEntity c = QContractJpaEntity.contractJpaEntity;

        if (from != null && to != null) return c.startDate.between(from, to);
        if (from != null) return c.startDate.goe(from);
        if (to != null) return c.startDate.loe(to);
        return null;
    }
```
#### 2) BooleanBuilder 방식
```java
@Override
public List<ContractJpaEntity> searchByCondition(ContractQuery query) {
    QContractJpaEntity c = QContractJpaEntity.contractJpaEntity;
    BooleanBuilder builder = new BooleanBuilder();

    if (query.status() != null) {
        builder.and(c.status.eq(query.status()));
    }

    LocalDate from = query.startDateFrom();
    LocalDate to = query.startDateTo();

    if (from != null && to != null) {
        builder.and(c.startDate.between(from, to));
    } else if (from != null) {
        builder.and(c.startDate.goe(from));
    } else if (to != null) {
        builder.and(c.startDate.loe(to));
    }

    return queryFactory.selectFrom(c)
            .where(builder)
            .fetch();
}
```

#### 3) QuerydslPredicateExecutor 방식
```java
public interface ContractJpaRepository extends
        JpaRepository<ContractJpaEntity, Long>,
        QuerydslPredicateExecutor<ContractJpaEntity> {
}

public List<Contract> searchByCondition(ContractQuery query) {
    QContractJpaEntity c = QContractJpaEntity.contractJpaEntity;
    BooleanBuilder builder = new BooleanBuilder();

    if (query.status() != null) {
        builder.and(c.status.eq(query.status()));
    }

    LocalDate from = query.startDateFrom();
    LocalDate to = query.startDateTo();

    if (from != null && to != null) {
        builder.and(c.startDate.between(from, to));
    } else if (from != null) {
        builder.and(c.startDate.goe(from));
    } else if (to != null) {
        builder.and(c.startDate.loe(to));
    }

    // 직접 repository에 전달
    return contractJpaRepository.findAll(builder).stream()
            .map(ContractMapper::toDomain)
            .toList();
}
```

#### 공통 문법
| QueryDSL 메서드                   | SQL 대응 문법                  | 설명               |
| ------------------------------ | -------------------------- | ---------------- |
| `.selectFrom(q)`               | `SELECT * FROM`            | 조회 대상 설정         |
| `.where(조건1, 조건2...)`          | `WHERE 조건 AND ...`         | 조건 설정 (null 무시됨) |
| `q.status.eq("ACTIVE")`        | `status = 'ACTIVE'`        | 값 동일 비교          |
| `q.age.goe(30)`                | `age >= 30`                | 이상 조건            |
| `q.age.loe(40)`                | `age <= 40`                | 이하 조건            |
| `q.date.between(a, b)`         | `BETWEEN a AND b`          | 범위 조건            |
| `.orderBy(q.createdAt.desc())` | `ORDER BY created_at DESC` | 정렬 조건            |
| `.fetch()`                     | 쿼리 실행 후 리스트 반환             | List 결과 반환       |
| `.fetchOne()`                  | 단건 반환                      | 결과가 하나일 때        |
| `.fetchFirst()`                | 첫 번째 건만 반환                 | limit 1          |

# 주의 할 점
### 1) Custom 인터페이스의 구현체 이름은 반드시 Impl로 끝나야 함
> The most important part of the class name that corresponds to the fragment interface is the Impl postfix. You can customize the store-specific postfix by setting @Enable<StoreModule>Repositories(repositoryImplementationPostfix = …).

출처) [spring docs](https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html#repositories.single-repository-behavior)
