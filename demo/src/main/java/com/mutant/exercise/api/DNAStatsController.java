package com.mutant.exercise.api;

import com.mutant.exercise.model.DNA;
import com.mutant.exercise.services.DNAService;
import com.mutant.exercise.services.DNAStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/stats")
@RestController
public class DNAStatsController {

    DNAStatsService dnaStatsService;

    @Autowired
    public DNAStatsController(DNAStatsService dnaStatsService) {
        this.dnaStatsService= dnaStatsService;
    }

    @GetMapping
    public List<DNA> getStats() {
        return dnaStatsService.listAllDNA();
    }
}
