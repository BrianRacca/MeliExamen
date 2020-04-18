package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

public class DiagonalUpSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return !(iCoordinate == sequence.size()-1 && jCoordinate == DNAProperties.MUTANT_ADN_SEQUENCE-1);
    }

    @Override
    public void next() {
        if(iCoordinate == 0 && iInicial!=sequence.size()-1) {
            iInicial++;
            iCoordinate=iInicial;
            jCoordinate=0;
        }else if(iCoordinate == 0 || jCoordinate==sequence.size()-1) {
            iCoordinate=iInicial;
            jInicial++;
            jCoordinate=jInicial;
        }else {
            iCoordinate--;
            jCoordinate++;
        }
    }

    @Override
    public void load() {
        iCoordinate= DNAProperties.MUTANT_ADN_SEQUENCE-1;
        jCoordinate=0;
        iInicial = iCoordinate;
        jInicial = 0;
    }

    public DiagonalUpSequence(List<String> dna) {
        sequence = dna;
    }
}
