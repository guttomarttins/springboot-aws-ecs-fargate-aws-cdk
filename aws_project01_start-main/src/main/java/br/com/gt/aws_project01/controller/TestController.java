package br.com.gt.aws_project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Test controller - name: {}", name);

        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/dog/color")
    public ResponseEntity<?> dogColor() {
        LOG.info("Test controller - Always black!");

        return ResponseEntity.ok("Always black!");
    }

    @GetMapping
    public ResponseEntity<?> getNumber() {

        int min = 1;
        int max = 10;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        LOG.info("Test controller - Number: {}", value);
        return ResponseEntity.ok("Number: " + value);
    }
}
