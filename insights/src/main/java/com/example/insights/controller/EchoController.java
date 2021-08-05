package com.example.insights.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class EchoController {

    private String name = "default-name";

    private final AtomicInteger counter = new AtomicInteger(0);

    @Value("${test.version:unknown}")
    private String testVersion;

    @GetMapping("/echo")
    public String getName() {
        if (counter.incrementAndGet() % 1000 == 0) {
            log.error("Unsupported count");
            throw new UnsupportedOperationException("Unsupported operation.");
        }

        log.info("Supported name {}", name);

        return String.format("%s/%s", name, testVersion);
    }

    @RequestMapping(value = "/echo/{name}", method = RequestMethod.PUT)
    public void setName(@PathVariable("name") String name) {
        this.name = name;
    }
}
