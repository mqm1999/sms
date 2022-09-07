package fa.training.dao;

import fa.training.entities.LineItem;

import java.sql.SQLException;
import java.util.List;

public interface LineItemDAO {
    List<LineItem> getAllItemsByOrderId(int orderId) throws SQLException;

    boolean addLineItem(LineItem item) throws SQLException;

}
