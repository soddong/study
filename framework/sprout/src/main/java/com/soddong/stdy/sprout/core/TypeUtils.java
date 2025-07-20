package com.soddong.stdy.sprout.core;

public class TypeUtils {
    public static boolean isSimpleType(Class<?> type) {
        return type.isPrimitive() || type == String.class ||
                Number.class.isAssignableFrom(type) ||
                type == Boolean.class || type == boolean.class;
    }
}

