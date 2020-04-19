package com.mutant.exercise.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
public class DNAStats {

    @Id
    private int id;

    private int humanCount;

    private int mutantCount;

    @Transient
    private Double ratio;

    public DNAStats(int humanCount, int mutantCount, Double ratio) {
        this.humanCount = humanCount;
        this.mutantCount = mutantCount;
        this.ratio = ratio;
    }

    public DNAStats(){}

    public int getHumanCount() {
        return humanCount;
    }

    public int getMutantCount() {
        return mutantCount;
    }

    public Double getRatio() {
        return (double)mutantCount/(double)humanCount;
    }
}
