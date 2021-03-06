package com.example.customer.repository;

import org.springframework.stereotype.Repository;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String CREATE_SQL = "CREATE TABLE IF NOT EXISTS customer (id serial not null, firstName text not null, " +
            "lastName text not null, phone text, email text, constraint customer_pkey primary key (id));";

    public void createTable() {
        jdbcTemplate.execute(CREATE_SQL);
    }

    private final String INSERT_SQL = "INSERT INTO customer (firstName, lastName, phone, email) VALUES (?,?,?,?)";

    @Override
    public void add(Customer customer) {
        jdbcTemplate.update(INSERT_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail());
    }

    private final String SELECT_BY_ID_SQL = "SELECT * FROM customer WHERE id = ?";

    @Override
    public Customer getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new CustomerMapper(), id);
    }

    private final String SELECT_SQL = "SELECT * FROM customer";

    @Override
    public List<Customer> get() {
        return jdbcTemplate.query(SELECT_SQL, new CustomerMapper());
    }

    private final String UPDATE_SQL = "UPDATE customer SET firstName=?, lastName=?, phone=?, email=? where id=?";

    @Override
    public void update(Customer customer, int id) {
        jdbcTemplate.update(UPDATE_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail(), id);
    }

    private final String DELETE_SQL = "DELETE FROM customer WHERE id=?";

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }


    // Map a row of the result set to a person object
    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstname"));
            customer.setLastName(rs.getString("lastname"));
            customer.setPhone(rs.getString("phone"));
            customer.setEmail(rs.getString("email"));
            return customer;
        }

    }

}

