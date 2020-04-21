package com.mutant.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.util.List;

@Entity
public class DNA {

    @Id
    @JsonIgnore
    private int id;

    @ElementCollection
    @NonNull
    private @NotEmpty(message = "Sequence cannot be null or empty") List<String> sequence;

    @NonNull
    @JsonIgnore
    private boolean mutant;

    public DNA(@JsonProperty("dna") List<String> sequence) {
        this.sequence = sequence;
        id = sequence.hashCode();
    }

    public DNA(){}

    /**
     * @return hashCode of sequence
     */
    public int getId() {
        return id;
    }

    /**
     * @return secuence of DNA
     */
    public List<String> getSequence() {
        return sequence;
    }

    /**
     * @return true: Mutant
     *         false: Human
     */
    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

}
