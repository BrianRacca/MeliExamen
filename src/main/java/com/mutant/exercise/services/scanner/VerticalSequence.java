package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

/**
 * Analyzes Vertical DNA Sequences
 */
public class VerticalSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return jCoordinate < sequence.size();
    }

    @Override
    public void next() {
        //If we know we are not going to find next sequence we skip to next Coordinate
        if(iterations<=1 && (sequence.size()-1-iCoordinate) < DNAProperties.MUTANT_ADN_SEQUENCE-1) resetNext();
             //Next line
        else if(iCoordinate < sequence.size()-1) iCoordinate++;
        else resetNext();
    }

    /**
     * next coordinate
     */
    @Override
    public void resetNext() {
        jCoordinate++;
        iCoordinate=0;

        reset();
    }

    @Override
    public void load() {
        iCoordinate=0;
        jCoordinate=0;
    }

    public VerticalSequence(List<String> dna) {
        sequence = dna;
    }
}
