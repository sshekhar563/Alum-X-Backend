package com.opencode.alumxbackend.basics.suzzzal.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencode.alumxbackend.basics.suzzzal.house.service.GreetingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/greet")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;

    @GetMapping
    public String greetUser(String name) {
        return greetingService.fetchGreeting(name);
    }
}
