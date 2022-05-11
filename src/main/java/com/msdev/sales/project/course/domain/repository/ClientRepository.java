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
    private static String UPDATE = "update client set name = ? where id = ?";
    private static String SELECT_ALL = "select * from client";
    private static String DELETE = "delete from client where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{
                client.getName(),
                client.getId()
        });

        return client;
    }

    public void delete(Client client) {
        delete(client.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Client> searchByName(String name) {
        return jdbcTemplate.query(SELECT_ALL.concat(" where name like ? "), new Object[]{"%" + name + "%"}, getClientMapper());
    }

    public List<Client> getAll() {
        return jdbcTemplate.query(SELECT_ALL, getClientMapper());
    }

    private RowMapper<Client> getClientMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Client(rs.getInt("id"), rs.getString("name"));
            }
        };
    }

}
