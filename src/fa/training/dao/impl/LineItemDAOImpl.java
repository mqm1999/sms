package fa.training.dao.impl;

import fa.training.dao.LineItemDAO;
import fa.training.entities.LineItem;
import fa.training.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    @Override
    public List<LineItem> getAllItemsByOrderId(int orderId) throws SQLException {
        List<LineItem> listLineItem = new ArrayList<>();
        String sql = "SELECT OrderId, ProductId, Quantity, Price FROM LineItem WHERE OrderId = ?;";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        while (resultSet.next()) {
            LineItem lineItem = new LineItem(
                    resultSet.getInt("OrderId"),
                    resultSet.getInt("ProductId"),
                    resultSet.getInt("Quantity"),
                    resultSet.getInt("Price")
            );
            listLineItem.add(lineItem);
        }
        return listLineItem;
    }


    @Override
    public boolean addLineItem(LineItem item) throws SQLException {
        String sql = "INSERT INTO LineItem (OrderId, ProductId, Quantity, Price) VALUES (?,?,?,?);";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, item.getOrderId());
        preparedStatement.setInt(2, item.getProductId());
        preparedStatement.setInt(3, item.getQuantity());
        preparedStatement.setDouble(4, item.getPrice());
        return preparedStatement.executeUpdate() > 0;
    }
}
