package com.mutant.exercise.services;

import com.mutant.exercise.dao.DNADao;
import com.mutant.exercise.exception.DNAValidationException;
import com.mutant.exercise.model.DNA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.mutant.exercise.exception.DNAValidationException.*;

@Service
public class DNAService {

    private static final Logger log = LoggerFactory.getLogger(DNAService.class);

    private static final Pattern pattern = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

    private final DNADao dnaDao;

    @Autowired
    public DNAService(@Qualifier("fakedao") DNADao dnaDao) {
        this.dnaDao = dnaDao;
    }

    public int addDNA(DNA dna) {
        return dnaDao.insertDNA(dna);
    }

    public List<DNA> listAllDNA() {
        return dnaDao.listAllDNA();
    }

    public Optional<DNA> find(int id) {
        return dnaDao.find(id);
    }

    public boolean isMutant(DNA dna) {
        if (!validateDNA(dna)) {
            log.error("You had one job.. DNA not valid for analisis");
            throw new DNAValidationException();
        }

        // We verify if the DNA was processed before
        final int id = dna.getSequence().hashCode();
        final Optional<DNA> existingDNA = dnaDao.find(id);
        if(existingDNA.isPresent()) {
            log.info("Not your first time here dude! the result will be the same!");
            return existingDNA.get().isMutant();
        }



        return dnaDao.isMutant(dna);
    }


    /**
     * @param dna The sequence of DNA being validated for length (NxN) and regix [atgc]
     * @return if the sequence is valid
     */
    private boolean validateDNA(DNA dna) {
        final List<String> sequence = dna.getSequence();

        return sequence.stream().allMatch(s -> pattern.matcher(s).matches() && s.length()==sequence.size());
    }
}
