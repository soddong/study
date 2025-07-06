plugins {
    java
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.soddong.stdy"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.projectlombok:lombok")

    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")

    // jmolecules
    implementation("org.jmolecules:jmolecules-ddd:1.9.0")

    // Lombok annotation processor (컴파일 시 자동 코드 생성용)
    annotationProcessor("org.projectlombok:lombok")

    // Q 클래스 생성을 위한 APT(Annotation Processor) 모듈
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")

    // @Entity, @Id 등의 JPA 어노테이션을 QueryDSL이 인식할 수 있도록 해주는 의존성
    annotationProcessor("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // @Generated 등 Jakarta 공통 어노테이션 인식을 위한 의존성 (빌드 중 오류 방지)
    annotationProcessor("jakarta.annotation:jakarta.annotation-api:2.1.1")

    // 개발 편의를 위해
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

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

tasks.withType<Test> {
    useJUnitPlatform()
}