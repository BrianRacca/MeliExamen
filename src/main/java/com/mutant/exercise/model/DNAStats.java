package com.mutant.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DNAStats {

    @Id
    private int id;

    @NonNull
    @JsonProperty("count_human_dna")
    private int mutantCount;

    @NonNull
    @JsonProperty("count_mutant_dna")
    private int humanCount;

    @Transient
    private double ratio;

    public DNAStats(int humanCount, int mutantCount) {
        this.humanCount = humanCount;
        this.mutantCount = mutantCount;
        ratio = 0;
    }

    public DNAStats(){}

    /**
     * @return calculates the ratio of Mutants (Mutants/Humans).
     */
    public double getRatio() {
        if(humanCount == 0) return mutantCount;
        if(mutantCount == 0) return 0;
        else return (double)mutantCount/(double)humanCount;
    }

    public int getHumanCount() {
        return humanCount;
    }

    public int getMutantCount() {
        return mutantCount;
    }

}
