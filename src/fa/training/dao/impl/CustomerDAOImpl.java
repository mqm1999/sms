package fa.training.dao.impl;

import fa.training.dao.CustomerDAO;
import fa.training.entities.Customer;
import fa.training.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAllCustomer() throws SQLException {
        String sql = "SELECT CustomerId, CustomerName FROM Customer";
        List<Customer> customerList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getInt("CustomerId"), resultSet.getString("CustomerName"));
            customerList.add(customer);
        }
        return customerList;
    }

    // id auto increment => only need name
    @Override
    public boolean addCustomer(String name) throws SQLException {
        String sql = "INSERT INTO Customer (CustomerName) VALUES (?);";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE CustomerId = ?;";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerId);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET CustomerName = ? WHERE CustomerId = ?;";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getCustomerName());
        preparedStatement.setInt(2, customer.getCustomerId());
        return preparedStatement.executeUpdate() > 0;
    }

    // string.format => dung cho execute
}
