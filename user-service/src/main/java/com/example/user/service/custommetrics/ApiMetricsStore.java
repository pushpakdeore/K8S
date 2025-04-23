package com.example.user.service.custommetrics;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ApiMetricsStore {
    private static final Map<String, List<Pair<Long, Long>>> apiTimings = new ConcurrentHashMap<>();

    public static void record(String uri, long duration) {
        long timestamp = System.currentTimeMillis();
        apiTimings.computeIfAbsent(uri, k -> new CopyOnWriteArrayList<>()).add(Pair.of(timestamp, duration));
    }

    public static String exportPrometheusFormat() {
        StringBuilder sb = new StringBuilder();
        long now = System.currentTimeMillis();
        long window = 5 * 60 * 1000; // 5 minutes

        for (Map.Entry<String, List<Pair<Long, Long>>> entry : apiTimings.entrySet()) {
            String path = entry.getKey();
            for (Pair<Long, Long> pair : entry.getValue()) {
                if (now - pair.getLeft() <= window) {
                    sb.append("api_response_time_ms{endpoint=\"").append(path).append("\"} ")
                            .append(pair.getRight()).append(" ").append(pair.getLeft()).append("\n");
                }
            }
        }
        return sb.toString();
    }
}
