package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

/**
 * Analyzes DiagonalUp DNA sequences
 */
public class DiagonalUpSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return !(iCoordinate == sequence.size()-1 && jCoordinate == DNAProperties.MUTANT_ADN_SEQUENCE-1);
    }

    @Override
    public void next() {
        //If i reach 0, iInicial it´s not the limit so i continue
        if(iCoordinate == 0 && iInicial!=sequence.size()-1) {
            iInicial++;
            iCoordinate=iInicial;
            jCoordinate=0;

            reset();
        //When i reach the extreme diagonal of the matrix i have to start going down again with jCoordinate++
        }else if(iCoordinate == 0 || jCoordinate==sequence.size()-1) {
            iCoordinate=iInicial;
            jInicial++;
            jCoordinate=jInicial;

            reset();
        }else {
            //How to move in diagonalUp Sequence
            iCoordinate--;
            jCoordinate++;
        }
    }

    /**
     * Reset variables so i dont get another unexpected match
     */
    public void reset() {
        letter="";
        iterations=0;
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
