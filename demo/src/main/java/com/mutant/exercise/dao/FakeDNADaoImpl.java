package com.mutant.exercise.dao;

import com.mutant.exercise.model.DNA;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakedao")
public class FakeDNADaoImpl implements DNADao {

    public static List<DNA> DB = new ArrayList<>();

    @Override
    public int insertDNA(DNA dna) {
        final DNA newDNA = new DNA(dna.getSequence());
        newDNA.setMutant(true);
        DB.add(newDNA);
        return 1;
    }

    @Override
    public List<DNA> listAllDNA() {
        return DB;
    }

    @Override
    public Optional<DNA> find(int id) {
        return DB.stream().filter(dna -> dna.getId()==id).findFirst();
    }

    @Override
    public boolean isMutant(DNA dna) {
        return true;
    }
}
