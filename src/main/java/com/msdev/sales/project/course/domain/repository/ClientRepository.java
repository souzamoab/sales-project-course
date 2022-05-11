package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {

    private static String INSERT = "insert into client (name) values (?)";
    private static String SELECT_ALL = "select * from client";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public List<Client> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Client(rs.getInt("id"), rs.getString("name"));
            }
        });
    }

}
