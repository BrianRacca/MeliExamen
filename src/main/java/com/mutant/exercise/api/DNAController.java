package com.mutant.exercise.api;

import com.mutant.exercise.model.DNA;
import com.mutant.exercise.services.DNAService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/mutant")
@RestController
public class DNAController {

    private final DNAService dnaService;

    @Autowired
    public DNAController(DNAService dnaService) {
        this.dnaService = dnaService;
    }

    /**
     * Searchs for al DNA objects stored
     *
     * @return all sequences stored
     */
    @GetMapping("/list")
    public List<DNA> listAllDNA() {
        return dnaService.listAllDNA();
    }

    /**
     * Searchs for mutant sequences in a DNA
     *
     * @param dna JSON format: {"dna": {"ATGC",...}}
     * @return OK        : MUTANT
     *         FORBIDDEN : HUMAN
     */
    @PostMapping
    public ResponseEntity<Void> isMutant(@Valid @NonNull @RequestBody DNA dna) {
        return dnaService.isMutant(dna);
    }
}
