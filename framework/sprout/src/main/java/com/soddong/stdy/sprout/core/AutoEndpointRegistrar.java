package com.soddong.stdy.sprout.core;

import com.soddong.stdy.sprout.annotation.AutoGet;
import com.soddong.stdy.sprout.annotation.AutoPost;
import com.soddong.stdy.sprout.annotation.RestEndpoint;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.aop.support.AopUtils;


import java.lang.reflect.Method;

@Component
public class AutoEndpointRegistrar implements ApplicationContextAware {

    private final RequestMappingHandlerMapping handlerMapping;

    public AutoEndpointRegistrar(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        System.out.println("[AutoEndpointRegistrar] Scanning for @RestEndpoint beans...");
        ctx.getBeansWithAnnotation(RestEndpoint.class).forEach((beanName, beanInstance) -> {
            Class<?> targetClass = AopUtils.getTargetClass(beanInstance);
            System.out.println("[AutoEndpointRegistrar] Found bean: " + beanName + " -> " + targetClass.getSimpleName());

            for (Method method : targetClass.getDeclaredMethods()) {
                String path;
                RequestMethod httpMethod = null;

                if (method.isAnnotationPresent(AutoGet.class)) {
                    httpMethod = RequestMethod.GET;
                    path = UriGenerator.generateUri(method);
                } else if (method.isAnnotationPresent(AutoPost.class)) {
                    httpMethod = RequestMethod.POST;
                    path = UriGenerator.generateUri(method);
                } else {
                    continue;
                }

                RequestMappingInfo mappingInfo = RequestMappingInfo
                        .paths(path)
                        .methods(httpMethod)
                        .build();

                handlerMapping.registerMapping(mappingInfo, beanInstance, method);
                System.out.println("[AutoEndpointRegistrar] Mapped " + httpMethod + " " + path + " -> " + method.getName());
            }
        });
    }

}
