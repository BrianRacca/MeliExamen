package com.mutant.exercise.bootstrap;

import com.mutant.exercise.dao.DNAStatsDaoImpl;
import com.mutant.exercise.model.DNAStats;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Optional;

/**
 * Data initalize needed for the application
 */
@Component
public class DataBoot implements CommandLineRunner {

    DNAStatsDaoImpl dnaStatsDao;

    public DataBoot(DNAStatsDaoImpl dnaStatsDao) {
        this.dnaStatsDao = dnaStatsDao;
    }

    @Override
    public void run(String... args) {

        //We create the only DNAStats we need for performance..
        final Optional<DNAStats> result = dnaStatsDao.findById(1);

        //Checking if is not the first time the app is running
        if(!result.isPresent()) {
            final DNAStats stats = new DNAStats(0, 0);
            dnaStatsDao.save(stats);
        }
    }
}
