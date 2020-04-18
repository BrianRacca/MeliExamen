package com.mutant.exercise.services;

import com.mutant.exercise.config.DNAProperties;
import com.mutant.exercise.dao.DNADao;
import com.mutant.exercise.exception.DNAValidationException;
import com.mutant.exercise.model.DNA;
import com.mutant.exercise.services.scanner.DiagonalDownSequence;
import com.mutant.exercise.services.scanner.DiagonalUpSequence;
import com.mutant.exercise.services.scanner.HorizontalSequence;
import com.mutant.exercise.services.scanner.VerticalSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.mutant.exercise.config.DNAProperties.*;
import static com.mutant.exercise.exception.DNAValidationException.errorMatch;
import static com.mutant.exercise.exception.DNAValidationException.errorSize;

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

    public ResponseEntity<Void> isMutant(DNA dna) {
        validateDNA(dna);

        // We verify if the DNA was processed before
        final int id = dna.getSequence().hashCode();
        final Optional<DNA> existingDNA = dnaDao.find(id);
        if(existingDNA.isPresent()) {
            log.info("Not your first time here dude! The result will be the same!");
            return response(existingDNA.get().isMutant());
        }

        final boolean mutant = match(dna);
        dna.setMutant(mutant);
        dnaDao.insertDNA(dna);

        return response(mutant);
    }

    private boolean match(DNA dna) {
        int totalSecuences = 0;

        final int horizontalSequences = new HorizontalSequence(dna.getSequence()).isMutant();
        totalSecuences+=horizontalSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        final int verticalSequences = new VerticalSequence(dna.getSequence()).isMutant();
        totalSecuences+=verticalSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        final int diagonalUpSequences = new DiagonalUpSequence(dna.getSequence()).isMutant();
        totalSecuences+=diagonalUpSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        final int diagonalDownSequences = new DiagonalDownSequence(dna.getSequence()).isMutant();
        totalSecuences+=diagonalDownSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        return false;
    }

    private ResponseEntity<Void> response(boolean mutant) {
        if(mutant) {
            log.info("You are a mutant... RESPECT");
            return ResponseEntity.ok().build();
        }
        else {
            log.info("You mere human... you will be destroyed by magneto");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * @param dna The sequence of DNA being validated for length (NxN) and regix [atgc]
     * @return if the sequence is valid
     */
    private void validateDNA(DNA dna) {
        final List<String> sequence = dna.getSequence();
        sequence.stream().allMatch(s -> {
            final boolean notMatch = !pattern.matcher(s).matches();
            final boolean invalidSize = s.length() != sequence.size();

            if(notMatch) throw new DNAValidationException(errorMatch);
            if(invalidSize) throw new DNAValidationException(errorSize);

            return true;
        } );
    }
}
