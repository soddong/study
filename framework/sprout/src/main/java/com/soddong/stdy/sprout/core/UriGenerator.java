package com.soddong.stdy.sprout.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class UriGenerator {
    public static String generateUri(Method method) {
        String methodName = method.getName();
        if (methodName.startsWith("get")) {
            methodName = methodName.substring(3); // getUserProfile -> UserProfile
        }
        String base = methodName.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();

        Parameter[] params = method.getParameters();
        if (params.length > 0 && params[0].getName().toLowerCase().contains("id")) {
            return "/" + base + "/{" + params[0].getName() + "}";
        } else {
            return "/" + base;
        }
    }
}
