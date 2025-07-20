package com.soddong.stdy.sprout.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AutoArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return true; // 모든 파라미터 처리
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Class<?> type = parameter.getParameterType();
        String name = parameter.getParameterName();

        if (name == null) {
            throw new IllegalArgumentException("Parameter name is not available. Compile with -parameters option.");
        }

        // 1. 단순 타입 처리 (query param 또는 path variable)
        if (TypeUtils.isSimpleType(type)) {
            String value = webRequest.getParameter(name);

            // PathVariable 값 찾기 (Spring 내부적으로 저장해둠)
            if (value == null) {
                Object pathVar = webRequest.getAttribute(
                        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                        NativeWebRequest.SCOPE_REQUEST
                );
                if (pathVar instanceof Map) {
                    value = ((Map<?, ?>) pathVar).get(name) != null ? ((Map<?, ?>) pathVar).get(name).toString() : null;
                }
            }

            if (value == null) {
                throw new IllegalArgumentException("Missing parameter: " + name);
            }

            // 변환
            if (type == int.class || type == Integer.class) return Integer.parseInt(value);
            if (type == long.class || type == Long.class) return Long.parseLong(value);
            if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
            return value;
        }

        // 2. 복합 객체 처리 (request body)
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String body = request.getReader().lines().collect(Collectors.joining());
        if (body.isBlank()) {
            throw new IllegalArgumentException("Request body is empty for type: " + type.getSimpleName());
        }
        return objectMapper.readValue(body, type);
    }
}
