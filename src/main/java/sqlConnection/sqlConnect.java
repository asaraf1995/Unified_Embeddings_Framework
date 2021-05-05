package main.java.sqlConnection;
import java.sql.*;
public class sqlConnect {
    String dbURL = "jdbc:mysql://localhost:3306/database_schema?serverTimezone=UTC";
    String username = "root";
    String password = "Kunwar_IIT13";
    Connection connection;
    public boolean connect() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM ml_temp;");
            return true;
            }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public Connection getConnection(){
        return connection;
    }
}
