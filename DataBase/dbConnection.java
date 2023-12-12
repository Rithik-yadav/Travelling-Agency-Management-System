package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
    public static Connection c;
    public static Statement st;

    static {
        try {
            // jdbc code here
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/fleetcontroldb", "root", "Rithik17yadav@");
            st = c.createStatement();
        } catch (Exception ex) {
            System.out.println("Not connected");
        }
    }
}
