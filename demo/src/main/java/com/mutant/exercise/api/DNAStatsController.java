package com.mutant.exercise.api;

import com.mutant.exercise.model.DNAStats;
import com.mutant.exercise.services.DNAStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stats")
@RestController
public class DNAStatsController {

    DNAStatsService dnaStatsService;

    @Autowired
    public DNAStatsController(DNAStatsService dnaStatsService) {
        this.dnaStatsService= dnaStatsService;
    }

    @GetMapping
    public DNAStats getStats() {
        return dnaStatsService.getStats();
    }
}
