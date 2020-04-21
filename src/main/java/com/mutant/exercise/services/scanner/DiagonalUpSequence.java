package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

/**
 * Analyzes DiagonalUp DNA sequences
 */
public class DiagonalUpSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return !(iCoordinate == sequence.size()-1 && jCoordinate == sequence.size()- (DNAProperties.MUTANT_ADN_SEQUENCE-1));
    }

    @Override
    public void next() {
        //When we reach iCoordinate 0 and iInicial is yet available to increase, Next Diagonal Coordinate (Ej: iInicial=3; sequence.size.lastIndex=4; [0][3] -> [4][0])
        if(iCoordinate == 0 && iInicial!=sequence.size()-1) {
            iInicial++;
            iCoordinate=iInicial;
            jCoordinate=0;

            reset();
              //Next diagonal Coordinate when we reached limit iCoordinate 0 or jCoordinate last value of Array (Ej: iInicial=sequence.size.lastIndex; sequence.size.lastIndex=4; [0][4] -> [4][1])
        }else if(iCoordinate == 0 || jCoordinate==sequence.size()-1) {
            iCoordinate=iInicial;
            jInicial++;
            jCoordinate=jInicial;

            reset();
         //Next Coordinate IN diagonal
        }else {
            iCoordinate--;
            jCoordinate++;
        }
    }

    private void nextLine() {
        iCoordinate = iInicial;
        jInicial++;
        jCoordinate = jInicial;
    }

    @Override
    public void resetNext() {
        iInicial++;
        iCoordinate=iInicial;
        jCoordinate=0;
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
