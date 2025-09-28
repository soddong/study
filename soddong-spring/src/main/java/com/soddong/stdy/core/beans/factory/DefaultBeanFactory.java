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
