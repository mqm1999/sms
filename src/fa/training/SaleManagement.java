package fa.training;

import fa.training.dao.impl.CustomerDAOImpl;
import fa.training.dao.impl.LineItemDAOImpl;
import fa.training.dao.impl.OrderDAOImpl;
import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SaleManagement {
    public static void main(String[] args) throws SQLException {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        LineItemDAOImpl lineItemDAO = new LineItemDAOImpl();
        Scanner sc = new Scanner(System.in);
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean flag = true;
        while (flag) {
            System.out.println("1. Get all customers");
            System.out.println("2. Add customer");
            System.out.println("3. Get items with id");
            System.out.println("4. Add item");
            System.out.println("5. Get orders with customer id: ");
            System.out.println("6. Add order: ");
            System.out.println("7. Update order total: ");
            System.out.println("Select an option: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    List<Customer> customerList = customerDAO.getAllCustomer();
                    for (Customer customer : customerList) {
                        System.out.println(customer);
                    }
                    break;

                case 2:
                    System.out.println("Input customer name: ");
                    String name = sc.nextLine();
                    if (customerDAO.addCustomer(name)) {
                        System.out.println("Add success");
                    } else {
                        System.out.println("Add fail");
                    }
                    break;

                case 3:
                    System.out.println("Input order ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    List<LineItem> lineItemList = lineItemDAO.getAllItemsByOrderId(id);
                    for (LineItem lineItem : lineItemList) {
                        System.out.println(lineItem);
                    }
                    break;

                case 4:
                    System.out.println("Input order id: ");
                    int orderId = Integer.parseInt(sc.nextLine());
                    System.out.println("Input product id: ");
                    int productId = Integer.parseInt(sc.nextLine());
                    System.out.println("Input quantity: ");
                    int quantity = Integer.parseInt(sc.nextLine());
                    System.out.println("Input price: ");
                    double price = Double.parseDouble(sc.nextLine());
                    LineItem item = new LineItem(orderId, productId, quantity, price);
                    if (lineItemDAO.addLineItem(item)) {
                        System.out.println("Add success");
                    } else {
                        System.out.println("Add fail");
                    }
                    break;

                case 5:
                    System.out.println("Input customer id: ");
                    int customerId = Integer.parseInt(sc.nextLine());
                    List<Order> orderList = orderDAO.getAllOrdersByCustomerId(customerId);
                    for (Order order : orderList) {
                        System.out.println(order);
                    }
                    break;

                case 6:
                    System.out.println("Input order date: ");
                    String orderDate = sc.nextLine();
                    System.out.println("Input customer id: ");
                    int customerIdForAddOrder = Integer.parseInt(sc.nextLine());
                    System.out.println("Input employee id: ");
                    int employeeIdForAddOrder = Integer.parseInt(sc.nextLine());
                    System.out.println("Input total: ");
                    double total = Double.parseDouble(sc.nextLine());
                    Order order = new Order(0, orderDate, customerIdForAddOrder, employeeIdForAddOrder, total);
                    if (orderDAO.addOrder(order)) {
                        System.out.println("Add success");
                    } else {
                        System.out.println("Add fail");
                    }
                    break;

                case 7:
                    System.out.println("Input order id need to update: ");
                    int updateOrderId = Integer.parseInt(sc.nextLine());
                    if (orderDAO.updateOrderTotal(updateOrderId)) {
                        System.out.println("Update success");
                    } else {
                        System.out.println("Update fail");
                    }
                    break;

                default:
                    flag = false;
            }

        }
    }
}
