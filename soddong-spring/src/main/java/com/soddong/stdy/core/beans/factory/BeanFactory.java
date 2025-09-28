package com.soddong.stdy.core.beans.factory;

public interface BeanFactory {
    <T> T getBean(Class<T> beanClass);
}
