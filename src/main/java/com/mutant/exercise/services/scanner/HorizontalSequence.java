package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

/**
 * Analyzes Horizontal DNA sequences
 */
public class HorizontalSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return iCoordinate < sequence.size();
    }

    @Override
    public void next() {
        //If we know we are not going to find next sequence we skip to next Coordinate
        if(iterations<=1 && (sequence.size()-1-jCoordinate) < DNAProperties.MUTANT_ADN_SEQUENCE-1) resetNext();
             // next column
        else if(jCoordinate < sequence.size()-1) jCoordinate++;
        else resetNext();
    }

    /**
     * next coordinate
     */
    @Override
    public void resetNext() {
        iCoordinate++;
        jCoordinate=0;

        reset();
    }

    @Override
    public void load() {
        iCoordinate=0;
        jCoordinate=0;
    }

    public HorizontalSequence(List<String> dna) {
        sequence = dna;
    }
}
