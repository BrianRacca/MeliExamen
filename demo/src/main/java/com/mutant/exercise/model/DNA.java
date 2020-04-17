package com.mutant.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class DNA {

    private final int id;

    private final @NotEmpty(message = "Sequence cannot be null or empty") List<String> sequence;

    private boolean mutant;

    public DNA(@JsonProperty("dna") List<String> sequence) {
        this.sequence = sequence;
        id = sequence.hashCode();
    }

    public int getId() {
        return id;
    }

    public List<String> getSequence() {
        return sequence;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

}
