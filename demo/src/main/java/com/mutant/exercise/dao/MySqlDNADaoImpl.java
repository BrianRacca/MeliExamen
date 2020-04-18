package com.mutant.exercise.dao;

import com.mutant.exercise.model.DNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysql")
public class MySqlDNADaoImpl implements DNADao {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    //Revisar esto esta mal
    @Override
    public int insertDNA(DNA dna) {
        jdbcTemplate.update("INSERT INTO dna (sequence, mutant) values ?", new RowMapper<DNA>() {
            @Override
            public DNA mapRow(ResultSet resultSet, int i) throws SQLException {
                final int id = resultSet.getInt("id");
                final Array a = resultSet.getArray("sequence");
                final String[] b = (String[]) a.getArray();
                final List<String> sequence = Arrays.asList(b);

                return new DNA(sequence);
            }
        }, dna) ;
        return 0;
    }

    @Override
    public List<DNA> listAllDNA() {
        final String sql = "SELECT id,sequence FROM dna";
        return jdbcTemplate.query(sql, new DNAMapper());
    }


    //Revisar una vez que cambiemos a hashCode
    @Override
    public Optional<DNA> find(int id) {
        final String sql = "SELECT * FROM dna WHERE id = ?";
        return jdbcTemplate.query(sql, new DNAMapper(), id).stream().findFirst();
    }

    @Override
    public boolean isMutant(DNA dna) {

        return true;
    }

    private static class DNAMapper implements RowMapper<DNA> {
        @Override
        public DNA mapRow(ResultSet resultSet, int i) throws SQLException {
            final Array a = resultSet.getArray("sequence");
            final String[] b = (String[]) a.getArray();
            final List<String> sequence = Arrays.asList(b);

            return new DNA(sequence);
        }
    }
}
