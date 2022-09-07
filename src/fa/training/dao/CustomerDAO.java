package fa.training.dao;

import fa.training.entities.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    List<Customer> getAllCustomer() throws SQLException;

    boolean addCustomer(String name) throws SQLException;

    boolean deleteCustomer(int customerId) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;
}
