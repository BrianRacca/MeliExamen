package com.mutant.exercise.services;

import com.mutant.exercise.dao.DNAStatsDaoImpl;
import com.mutant.exercise.model.DNAStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DNAStatsService {
    private static final Logger log = LoggerFactory.getLogger(DNAStatsService.class);

    DNAStatsDaoImpl dnaStatsDao;

    @Autowired
    public DNAStatsService(DNAStatsDaoImpl dnaStatsDao) {
        this.dnaStatsDao = dnaStatsDao;
    }

    /**
     * @return DNAStats object
     */
    public DNAStats getStats() {
        return dnaStatsDao.findAll().iterator().next();
    }

    /**
     * calls daoImpl to increment human by 1
     */
    public void incrementHuman() {
        dnaStatsDao.incrementHuman();
    }

    /**
     * calls daoImpl to increment mutant by 1
     */
    public void incrementMutant() {
        dnaStatsDao.incrementMutant();
    }

}
