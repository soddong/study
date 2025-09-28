package com.soddong.stdy.core.beans.factory;

import com.soddong.stdy.core.context.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultBeanFactory implements BeanFactory {

    private final Map<Class<?>, Object> singletons = new HashMap<>();

    public <T> void registerBean(Class<?> type, Object instance) {
        log.info("빈 등록: {} -> {}", type.getName(), instance);
        singletons.put(type, instance);
        performInjection(instance);
    }

    private void performInjection(Object bean) {
        log.debug("빈 [{}] 에 대해 @Autowired 의존성 주입 시작", bean.getClass().getName());

        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                log.info("@Autowired 필드 발견: {} (대상 클래스: {})", field.getName(), bean.getClass().getName());
                Object dependency = getBean(field.getType()); // 타입 기반 조회
                if (dependency == null) {
                    log.error("주입할 빈을 찾을 수 없음: {} (필드: {} in {})",
                            field.getType().getName(), field.getName(), bean.getClass().getName());
                    throw new RuntimeException("의존성 주입 실패: " + field);
                }
                try {
                    field.setAccessible(true);
                    field.set(bean, dependency);
                    log.info("의존성 주입 완료: {} -> {}.{}",
                            dependency.getClass().getName(), bean.getClass().getName(), field.getName());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("의존성 주입 실패 " + field, e);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> beanClass) {
        Object bean = singletons.get(beanClass);
        if (bean == null) {
            log.warn("요청한 타입의 빈이 없음: {}", beanClass.getName());
            return null;
        }
        log.info("빈 조회 성공: {} -> {}", beanClass.getName(), bean);
        return (T) bean;
    }
}
