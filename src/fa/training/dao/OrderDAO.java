package fa.training.dao;

import fa.training.entities.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrdersByCustomerId(int customerId) throws SQLException;

    boolean addOrder(Order order) throws SQLException;

    Double computeOrderTotal(int orderId) throws SQLException;

    boolean updateOrderTotal(int orderId) throws SQLException;
}
