package com.mutant.exercise.services.scanner;

import java.util.List;

public class VerticalSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return jCoordinate < sequence.size();
    }

    @Override
    public void next() {
        if(iCoordinate < sequence.size()-1){
            iCoordinate++;
        }
        else {
            jCoordinate++;
            iCoordinate=0;

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

    public VerticalSequence(List<String> dna) {
        sequence = dna;
    }
}
