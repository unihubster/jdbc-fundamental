package net.demo.jdbcfundamental;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {

    public static void main(String[] args) {
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "root");
        try (Connection connection = connectionManager.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.findById(10000);
            System.out.println(customer);
            customer.setEmail("gwashington@wh.gov");
            customer = customerDAO.update(customer);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
