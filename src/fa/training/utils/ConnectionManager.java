package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static String url = "jdbc:sqlserver://localhost:1433;database=SMS;encrypt=true;trustServerCertificate=true";
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String username = "sa";
    public static String password = "Killian281199?";
    public static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection success");
            } catch (SQLException e) {
                System.out.println("Fail");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed");
        }
        return connection;
    }
}
