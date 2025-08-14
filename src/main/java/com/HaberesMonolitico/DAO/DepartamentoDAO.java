package com.HaberesMonolitico.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DepartamentoDAO {

    private final JdbcTemplate jdbcTemplate;

    public DepartamentoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> obtenerNombresDepartamentos() {
        return jdbcTemplate.queryForList("SELECT nombre FROM departamento", String.class);
    }
}

