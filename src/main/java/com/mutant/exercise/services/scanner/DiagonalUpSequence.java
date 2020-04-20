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
        //Skip to nextline if no iterations were found and iInicial is at it´s limit and we haven´t found more than 1 iteration
        if(iInicial == sequence.size()-1 && iCoordinate-jCoordinate < DNAProperties.MUTANT_ADN_SEQUENCE-1 && iterations<1){
            nextLine();
            return;
        }
        //If i reach 0 and iInicial it´s not the limit so i continue
        if(iCoordinate == 0 && iInicial!=sequence.size()-1) {
            resetNext();
            reset();
        //When i reach the extreme diagonal of the matrix i have to start going down again with jCoordinate++
        }else if(iCoordinate == 0 || jCoordinate==sequence.size()-1) {
            nextLine();
            reset();

              //Skip to next line before we reach the iCoordinate limit and jCoordinate starts to move right
        }else if(iCoordinate< DNAProperties.MUTANT_ADN_SEQUENCE-1 && iterations <=1) resetNext();
        else {
            //How to move in diagonalUp Sequence
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
