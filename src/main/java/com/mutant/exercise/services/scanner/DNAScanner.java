package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class DNAScanner {

    public int iCoordinate;

    public int jCoordinate;

    public int jInicial;

    public int iInicial;

    public int sequencesFound;

    public int iterations;

    public String letter = "";

    public List<String> sequence = new ArrayList<>();

    public abstract boolean hasNext();

    public abstract void next();

    public abstract void load();

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
