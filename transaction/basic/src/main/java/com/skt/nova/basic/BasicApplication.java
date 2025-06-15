package com.skt.nova.basic;

import com.skt.nova.basic.domain.port.in.AccountCreateUseCase;
import com.skt.nova.basic.domain.port.in.AccountReadUseCase;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootApplication
public class BasicApplication {

    @Autowired
    private AccountCreateUseCase createUseCase;

    @Autowired
    private AccountReadUseCase readUseCase;

    @PostConstruct
    public void testScenario() {
        log.info("======== Test Scenario ========");
        try {
            createUseCase.createAndUpdateThenFail();
        } catch (RuntimeException e) {
            log.warn("Exception : {}", e.getMessage());
        }

        // 결과 확인
        readUseCase.read(1L);
    }

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }
}
