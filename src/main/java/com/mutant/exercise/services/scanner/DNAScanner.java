package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class DNAScanner {

    /**
     *
     */
    public int iCoordinate;

    public int jCoordinate;

    public int jInicial;

    public int iInicial;

    /**
     * sequences found defined by DNAProperties.MUTANT_ADN_SEQUENCE
     */
    public int sequencesFound;

    /**
     * iterations found with a specific letter
     */
    public int iterations;

    /**
     * letter being compared
     */
    public String letter = "";

    /**
     * dna sequence NxN
     */
    public List<String> sequence = new ArrayList<>();

    /**
     * depending the object values, evaluate if can continue
     *
     * @return if can continue searching
     */
    public abstract boolean hasNext();

    /**
     * change values to next iteration
     */
    public abstract void next();

    /**
     * load inital values to start iterating
     */
    public abstract void load();

    /**
     * skip iteration to next probably sequence
     */
    public abstract void resetNext();

    /**
     * reset variables so i dont get another unexpected match
     */
    public void reset() {
        letter = "";
        iterations = 0;
    }

    /**
     * depending the object implementation will find in a determined way for sequences
     *
     * @return true if found sequence pattern defined
     */
    public int isMutant() {
        load();

        while(hasNext()) {
            final String actualLetter = Character.toString(sequence.get(iCoordinate).charAt(jCoordinate));
            letter = letter.equals("") ? actualLetter : letter;
            if(letter.equals(actualLetter)) iterations++;
            else {
                letter = actualLetter;
                iterations = 1;
            }

            if (iterations == DNAProperties.MUTANT_ADN_SEQUENCE) sequencesFound++;
            if(sequencesFound >=2) break;
            next();
        }

        return sequencesFound;
    }
}
