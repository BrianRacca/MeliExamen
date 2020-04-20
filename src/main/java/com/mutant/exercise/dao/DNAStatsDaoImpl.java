package com.mutant.exercise.dao;

import com.mutant.exercise.model.DNAStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Repository DNAStats object
 */
@Repository
public interface DNAStatsDaoImpl extends JpaRepository<DNAStats, Integer> {

    @Modifying
    @Query(value = "UPDATE dnastats SET human_count = human_count + 1", nativeQuery = true)
    @Transactional
    void incrementHuman();

    @Modifying
    @Query(value = "UPDATE dnastats SET mutant_count = mutant_count + 1", nativeQuery = true)
    @Transactional
    void incrementMutant();

}
