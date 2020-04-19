package com.mutant.exercise.services;

import com.mutant.exercise.dao.DNADao;
import com.mutant.exercise.dao.DNADaoImpl;
import com.mutant.exercise.dao.DNAStatsDaoImpl;
import com.mutant.exercise.model.DNA;
import com.mutant.exercise.model.DNAStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class DNAStatsService {
    private static final Logger log = LoggerFactory.getLogger(DNAStatsService.class);

    DNAStatsDaoImpl dnaStatsDao;

    @Autowired
    public DNAStatsService(DNAStatsDaoImpl dnaStatsDao) {
        this.dnaStatsDao = dnaStatsDao;
    }

    public DNAStats getStats() {
        return dnaStatsDao.findAll().iterator().next();
    }

    public void incrementHuman() {
        dnaStatsDao.incrementHuman();
    }

    public void incrementMutant() {
        dnaStatsDao.incrementMutant();
    }

}
