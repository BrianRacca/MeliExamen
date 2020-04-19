package com.mutant.exercise.dao;

import com.mutant.exercise.model.DNA;

import java.util.List;
import java.util.Optional;

public interface DNADao {

    int insertDNA(DNA dna);

    List<DNA> listAllDNA();

    Optional<DNA> find(int id);

    boolean isMutant(DNA dna);

}
