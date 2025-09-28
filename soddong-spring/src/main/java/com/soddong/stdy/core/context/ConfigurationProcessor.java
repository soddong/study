package com.soddong.stdy.core.context;

import com.soddong.stdy.core.beans.factory.DefaultBeanFactory;
import com.soddong.stdy.core.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Slf4j
public class ConfigurationProcessor {

    private final DefaultBeanFactory factory;

    public ConfigurationProcessor(DefaultBeanFactory factory) {
        this.factory = factory;
    }

    public void process(Class<?> configClass) {
        try {
            log.info("Processing configuration class: {}", configClass.getName());
            Object configInstance = configClass.getDeclaredConstructor().newInstance();

            for (Method method : configClass.getMethods()) {
                if (method.isAnnotationPresent(Bean.class)) {
                    log.info("Found @Bean method: {}", method.getName());
                    Object bean = method.invoke(configInstance);

                    @SuppressWarnings("unchecked")
                    Class<Object> returnType = (Class<Object>) method.getReturnType();
                    log.info("Registering bean of type {} with instance {}", returnType.getName(), bean);
                    factory.registerBean(returnType, bean);
                }
            }

        } catch (Exception exception) {
            log.error("설정 클래스 처리 실패: {}", configClass, exception);
            throw new RuntimeException("설정 클래스 처리 실패: " + configClass, exception);
        }
    }
}
