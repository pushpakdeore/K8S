package com.example.user.service.custommetrics;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiTimingInterceptor implements HandlerInterceptor {
    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(START_TIME, System.nanoTime());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long startTime = (Long) request.getAttribute(START_TIME);
        if (startTime != null) {
            long durationMs = (System.nanoTime() - startTime) / 1_000_000;
            ApiMetricsStore.record(request.getRequestURI(), durationMs);
        }
    }
}
