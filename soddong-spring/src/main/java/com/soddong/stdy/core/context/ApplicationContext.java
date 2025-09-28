package com.soddong.stdy.core.context;

import com.soddong.stdy.core.beans.factory.BeanFactory;
import com.soddong.stdy.core.beans.factory.DefaultBeanFactory;

public class ApplicationContext implements BeanFactory {

    private final DefaultBeanFactory beanFactory = new DefaultBeanFactory();

    public ApplicationContext(Class<?>... configClasses) {
        ConfigurationProcessor processor = new ConfigurationProcessor(beanFactory);
        for (Class<?> configClass : configClasses) {
            processor.process(configClass);
        }
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return beanFactory.getBean(beanClass);
    }

}
