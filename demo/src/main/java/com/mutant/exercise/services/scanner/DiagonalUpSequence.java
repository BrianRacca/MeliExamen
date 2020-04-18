package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

public class DiagonalUpSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return iCoordinate==sequence.size()-1 && jCoordinate==0;
    }

    @Override
    public void next() {
        iCoordinate--;
        jCoordinate++;
    }

    @Override
    public void load() {
        iCoordinate= DNAProperties.MUTANT_ADN_SEQUENCE-1;
        jCoordinate=0;
    }

    public DiagonalUpSequence(List<String> dna) {
        sequence = dna;
    }
}
