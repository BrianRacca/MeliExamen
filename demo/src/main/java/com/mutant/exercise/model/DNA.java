package com.mutant.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class DNA {

    @Id
    private int id;

    @ElementCollection
    private @NotEmpty(message = "Sequence cannot be null or empty") List<String> sequence;

    private boolean mutant;

    public DNA(@JsonProperty("dna") List<String> sequence) {
        this.sequence = sequence;
        id = sequence.hashCode();
    }

    public DNA(){}

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
