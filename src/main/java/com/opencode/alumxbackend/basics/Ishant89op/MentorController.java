package com.opencode.alumxbackend.basics.Ishant89op;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MentorController {
    @GetMapping("/basics/mentor")
    public Map<String, String> mentor() {
        return Map.of("message", "guidance");
    }
}