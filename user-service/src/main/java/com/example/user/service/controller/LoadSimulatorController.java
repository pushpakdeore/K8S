package com.example.user.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/load")
public class LoadSimulatorController {
    private static final Logger logger = LoggerFactory.getLogger(LoadSimulatorController.class);

    @GetMapping("/heavy")
    public String doHeavyWork() {
        for (int i = 0; i < 100000; i++) {
            System.out.print("ok ");
        }
        return "Heavy work done";
    }

    @GetMapping("/test3")
    public String test() {
        logger.warn("test3 Hello Controller ");
        return "Welcome";
    }

    @GetMapping("/test4")
    public String test2() {
        logger.warn("test4 Hello Controller ");
        return "Welcome";
    }
    private final List<byte[]> memoryConsumers = new ArrayList<>();

    @GetMapping("/spike")
    public String spikeMemory(@RequestParam(defaultValue = "10") int mb) {
        // Convert MB to bytes
        int bytes = mb * 1024 * 1024;

        // Allocate memory
        byte[] block = new byte[bytes];
        memoryConsumers.add(block);

        return "Allocated " + mb + " MB of memory.";
    }

    @GetMapping("/clear")
    public String clearMemory() {
        memoryConsumers.clear();
        System.gc();
        return "Cleared memory.";
    }
}
