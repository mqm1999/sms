package fa.training.dao.impl;

import fa.training.dao.OrderDAO;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.utils.ConnectionManager;
import fa.training.utils.DateStringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    LineItemDAOImpl lineItemDAO = new LineItemDAOImpl();
    DateStringConverter dateStringConverter = new DateStringConverter();

    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> listOrder = new ArrayList<>();
        String sql = "SELECT OrderId, OrderDate, CustomerId, EmployeeId, Total FROM [Order] WHERE CustomerId = ?;";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order(
                    resultSet.getInt("OrderId"),
                    dateStringConverter.convertStringToLocalDateTime(resultSet.getString("OrderDate")),
                    resultSet.getInt("CustomerId"),
                    resultSet.getInt("EmployeeId"),
                    resultSet.getDouble("Total")
            );
            listOrder.add(order);
        }
        return listOrder;
    }

    @Override
    public boolean addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO [Order] (OrderDate, CustomerId, EmployeeId, Total) VALUES (?,?,?,?);";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, dateStringConverter.convertLocalDateTimeToString(order.getOrderDate()));
        preparedStatement.setInt(2, order.getCustomerId());
        preparedStatement.setInt(3, order.getEmployeeId());
        preparedStatement.setDouble(4, order.getTotal());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Double computeOrderTotal(int orderId) throws SQLException {
        List<LineItem> listLineItem = lineItemDAO.getAllItemsByOrderId(orderId);
        double orderTotal = 0D;
        for (LineItem lineItem : listLineItem) {
            orderTotal = lineItem.getQuantity() * lineItem.getPrice();
        }
        return orderTotal;
    }

    @Override
    public boolean updateOrderTotal(int orderId) throws SQLException {
        double oldOrderTotal = computeOrderTotal(orderId);
        String sql = "UPDATE [Order] SET Total = ? WHERE OrderId = ?;";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, oldOrderTotal);
        preparedStatement.setInt(2, orderId);
        return preparedStatement.executeUpdate() > 0;
    }
}
