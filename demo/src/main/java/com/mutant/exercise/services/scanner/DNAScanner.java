package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class DNAScanner {

    public int iCoordinate;

    public int jCoordinate;

    public int iInicial;

    public int sequencesFound;

    public int iterations;

    public List<String> sequence = new ArrayList<>();

    public abstract boolean hasNext();

    public abstract void next();

    public abstract void load();

    public int isMutant() {
        load();

        String letter = "";
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

    public static boolean isMutant(List<String> dna) {
        // We keep track of secuences found in the ADN!
        int horizontalSecuences = 0;
        int verticalSecuences = 0;
        int diagonalSecuences = 0;

        for (final String s : dna) {
            String input = null;
            int horizontalIterations = 0;
            int verticalIterations = 0;
            int diagonalIterations = 0;

            for (int j = 0; j < s.length(); j++) {
                if(j < Integer.compare(s.length(), DNAProperties.MUTANT_ADN_SEQUENCE)) {
                    // Horizontal
                    final String code = Character.toString(s.charAt(j));
                    if (input == null) input = code;
                    else if (input.equals(code) && horizontalIterations < DNAProperties.MUTANT_ADN_SEQUENCE)
                        horizontalIterations++;

                    if (!input.equals(code)) {
                        input = code;
                        horizontalIterations = 0;
                    }

                    if (horizontalIterations == 3) horizontalSecuences++;
                }

                // Vertical


                // Diagonal
            }

            if((horizontalSecuences + verticalSecuences + diagonalSecuences) >= DNAProperties.SEQUENCES_MUTANT) return true;
        }

        return false;


    }
}
