package com.mutant.exercise.services.scanner;

import com.mutant.exercise.config.DNAProperties;

import java.util.List;

/**
 * Analyzes DiagonalDown DNA sequences
 */
public class DiagonalDownSequence extends DNAScanner {
    @Override
    public boolean hasNext() {
        return !(iCoordinate == 0 && jCoordinate == DNAProperties.MUTANT_ADN_SEQUENCE-1);
    }

    @Override
    public void next() {
        if(iCoordinate == sequence.size()-1 && iInicial!=0) {
            iInicial--;
            iCoordinate=iInicial;
            jCoordinate=0;

            //Reset
            letter="";
            iterations=0;
        }else if(iCoordinate == 0 && iInicial==0) {
            iCoordinate++;
            jCoordinate++;
        }
        else {
            if(iCoordinate == 0 || jCoordinate==sequence.size()-1) {
                iCoordinate=iInicial;
                jInicial++;
                jCoordinate=jInicial;

                //Reset
                letter="";
                iterations=0;
            }else {
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

    public DiagonalDownSequence(List<String> dna) {
        sequence = dna;
    }
}
