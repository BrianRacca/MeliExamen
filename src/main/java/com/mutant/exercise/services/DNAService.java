package com.mutant.exercise.services;

import com.mutant.exercise.config.DNAProperties;
import com.mutant.exercise.dao.DNADaoImpl;
import com.mutant.exercise.exception.DNAValidationException;
import com.mutant.exercise.model.DNA;
import com.mutant.exercise.services.scanner.DiagonalDownSequence;
import com.mutant.exercise.services.scanner.DiagonalUpSequence;
import com.mutant.exercise.services.scanner.HorizontalSequence;
import com.mutant.exercise.services.scanner.VerticalSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.mutant.exercise.config.DNAProperties.SEQUENCES_MUTANT;
import static com.mutant.exercise.exception.DNAValidationException.ERROR_MATCH;
import static com.mutant.exercise.exception.DNAValidationException.ERROR_SIZE;

@Service
public class DNAService {

    private static final Logger log = LoggerFactory.getLogger(DNAService.class);

    private static final Pattern pattern = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

    private final DNADaoImpl dnaDaoImpl;

    private DNAStatsService dnaStatsService;

    @Autowired
    public DNAService(DNADaoImpl dnaDaoImpl, DNAStatsService dnaStatsService) {
        this.dnaDaoImpl = dnaDaoImpl;
        this.dnaStatsService = dnaStatsService;
    }

    /**
     * @return all dna sequences stored
     */
    public List<DNA> listAllDNA() {
        return dnaDaoImpl.findAll();
    }

    /**
     * @param id hashCode of sequence
     * @return tries to return the DNA Object found
     */
    public Optional<DNA> find(int id) {
        return dnaDaoImpl.findById(id);
    }

    /**
     * process the sequence to know if it is mutant or human
     *
     * @param argDna DNA Object
     * @return Response OK          MUTANT
     *         Response FORBIDDEN   HUMAN
     */
    public ResponseEntity<Void> isMutant(DNA argDna) {
        validateDNA(argDna);
        final List<String> dna = sequenceToUpper(argDna);

        // We verify if the DNA was processed before
        final int id = dna.hashCode();
        final Optional<DNA> existingDNA = dnaDaoImpl.findById(id);
        if(existingDNA.isPresent()) {
            log.info("Not your first time here dude! The result will be the same!");
            return response(existingDNA.get().isMutant());
        }

        final boolean mutant = match(dna);
        argDna.setMutant(mutant);
        dnaDaoImpl.save(argDna);

        return response(mutant);
    }

    /**
     * @param argDna DNA Object
     * @return  sequence to UPPERCASE
     */
    private List<String> sequenceToUpper(DNA argDna) {
        // If the DNA is correct we transform it to upper case
        List<String> dna = new ArrayList<>();
        argDna.getSequence().forEach(s -> dna.add(s.toUpperCase()));
        return dna;
    }

    /**
     * @param dna sequences of dna
     * @return true: 2 matching sequences or more
     *         false: less than 2 matches
     */
    private boolean match(List<String> dna) {
        int totalSecuences = 0;

        final int horizontalSequences = new HorizontalSequence(dna).isMutant();
        log.info("HorizontalSequences found: " + horizontalSequences);
        totalSecuences+=horizontalSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        final int verticalSequences = new VerticalSequence(dna).isMutant();
        log.info("VerticalSequences found : " + verticalSequences);
        totalSecuences+=verticalSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        final int diagonalUpSequences = new DiagonalUpSequence(dna).isMutant();
        log.info("DiagonalUpSequences found: " + diagonalUpSequences);
        totalSecuences+=diagonalUpSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;

        final int diagonalDownSequences = new DiagonalDownSequence(dna).isMutant();
        log.info("DiagonalDownSequences found: " + diagonalDownSequences);
        totalSecuences+=diagonalDownSequences;
        if(totalSecuences >= SEQUENCES_MUTANT) return true;


        return false;
    }

    /**
     * @param mutant if sequence matchs with mutant
     * @return Response OK if MUTANT
     *         Response FORBIDDEN if HUMAN
     */
    private ResponseEntity<Void> response(boolean mutant) {
        if(mutant) {
            log.info("You are a mutant... RESPECT");
            dnaStatsService.incrementMutant();
            return ResponseEntity.ok().build();
        }
        else {
            log.info("You mere human... you will be destroyed by magneto");
            dnaStatsService.incrementHuman();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * @param dna The sequence of DNA being validated for length (NxN) and regix [atgc]
     * @return if the sequence is valid
     */
    private void validateDNA(DNA dna) {
        final List<String> sequence = dna.getSequence();

        if(dna.getSequence().size()<DNAProperties.MUTANT_ADN_SEQUENCE) throw new DNAValidationException(ERROR_SIZE);

        sequence.stream().allMatch(s -> {
            final boolean notMatch = !pattern.matcher(s).matches();
            final boolean invalidSize = s.length() != sequence.size();

            if(notMatch) throw new DNAValidationException(ERROR_MATCH);
            if(invalidSize) throw new DNAValidationException(ERROR_SIZE);

            return true;
        } );
    }
}
