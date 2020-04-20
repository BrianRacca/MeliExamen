package com.mutant.exercise.dao;

import com.mutant.exercise.model.DNA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository of DNA object
 */
@Repository
public interface DNADaoImpl extends CrudRepository<DNA, Integer> {

    @Override
    List<DNA> findAll();
}
