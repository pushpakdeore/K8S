package com.example.user.service.custommetrics;

public class JvmMetricsCollector {

    public static String getJvmMetrics() {
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory(); // bytes
        long freeMemory = runtime.freeMemory();   // bytes
        long usedMemory = totalMemory - freeMemory; // bytes
        long maxMemory = runtime.maxMemory();     // bytes

        long timestamp = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        sb.append("jvm_memory_used_bytes ").append(usedMemory).append(" ").append(timestamp).append("\n");
        sb.append("jvm_memory_free_bytes ").append(freeMemory).append(" ").append(timestamp).append("\n");
        sb.append("jvm_memory_total_bytes ").append(totalMemory).append(" ").append(timestamp).append("\n");
        sb.append("jvm_memory_max_bytes ").append(maxMemory).append(" ").append(timestamp).append("\n");

        return sb.toString();
    }
}
