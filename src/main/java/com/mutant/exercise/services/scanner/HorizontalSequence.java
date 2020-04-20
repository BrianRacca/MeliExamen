package com.mutant.exercise.services.scanner;

import java.util.List;

public class HorizontalSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return iCoordinate < sequence.size();
    }

    @Override
    public void next() {
        if(jCoordinate < sequence.size()-1) jCoordinate++;
        else {
            iCoordinate++;
            jCoordinate=0;

            //Reset
            iterations=0;
            letter="";
        }
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
