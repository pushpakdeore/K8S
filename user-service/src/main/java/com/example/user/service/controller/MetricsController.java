package com.example.user.service.controller;

import com.example.user.service.custommetrics.ApiMetricsStore;
import com.example.user.service.custommetrics.JvmMetricsCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    @GetMapping(value = "/custom-metrics", produces = "text/plain")
    public String getMetrics() {
        StringBuilder metrics = new StringBuilder();

        metrics.append(ApiMetricsStore.exportPrometheusFormat());
        metrics.append(JvmMetricsCollector.getJvmMetrics());

        // Add required end-of-file marker
        metrics.append("# EOF\n");

        return metrics.toString();
    }
}
