package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

/**
 * Analyzes DiagonalDown DNA sequences
 */
public class DiagonalDownSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return !(iCoordinate == 0 && jCoordinate == sequence.size()-(DNAProperties.MUTANT_ADN_SEQUENCE-1));
    }

    @Override
    public void next() {
        //When we reach iCoordinate secuence.size.lastIndex and iInicial is yet available to decrement, Next Diagonal Coordinate (Ej: iInicial=1; sequence.size.lastIndex=4; [4][3] -> [0][0])
        if(iCoordinate == sequence.size()-1 && iInicial!=0) {
            iInicial--;
            iCoordinate=iInicial;
            jCoordinate=0;

            reset();
            //When we reach iCoordinate = 0 and iInicial = 0 it means we start going into the diagonal down with iCoordinate=jCoordinate
        }else if(iCoordinate == 0 && iInicial==0) {
            iCoordinate++;
            jCoordinate++;
        }
        else {
            //Next diagonal Coordinate when we reached limit jCoordinate last value of Array (Ej: iInicial=0; sequence.size.lastIndex=4; [4][4] -> [0][1])
            if(jCoordinate==sequence.size()-1) {
                iCoordinate=iInicial;
                jInicial++;
                jCoordinate=jInicial;

                reset();
            }else {
                //We keep going down the diagonal
                iCoordinate++;
                jCoordinate++;
            }
        }
    }

    @Override
    public void load() {
        iCoordinate= sequence.size()-DNAProperties.MUTANT_ADN_SEQUENCE;
        jCoordinate=0;
        iInicial = iCoordinate;
        jInicial = 0;
    }

    @Override
    public void resetNext() {

    }

    public DiagonalDownSequence(List<String> dna) {
        sequence = dna;
    }
}
