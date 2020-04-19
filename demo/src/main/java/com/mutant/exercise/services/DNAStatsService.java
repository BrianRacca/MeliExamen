package com.mutant.exercise.services;

import com.mutant.exercise.model.DNA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DNAStatsService {
    private static final Logger log = LoggerFactory.getLogger(DNAStatsService.class);



    public List<DNA> getStats() {
        return new ArrayList<>();//dnaDao.listAllDNA();

    }

}
