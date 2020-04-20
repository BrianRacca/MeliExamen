package com.mutant.exercise.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index App
 */
@RequestMapping("/")
@RestController
public class IndexController {

    @GetMapping
    public String getTitleApp() {
        return "Welcome to MUTANT ANALIST app";
    }
}
