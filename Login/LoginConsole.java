package Login;

import DataBase.dbConnection;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginConsole {

    public static boolean login() {
        System.out.println("\n --------------LOGIN PAGE--------------\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Login ID: ");
        String username = scanner.nextLine();
        Console cb=System.console();
        if (cb == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        char[] passwordArray = cb.readPassword("Enter password \n");
        String password = new String(passwordArray);

        System.out.println("Password Length : " + password.length() + ".");

        try {
            String sql = "SELECT * FROM login WHERE id = '" + username + "' AND password = '" + password + "'";
            try (ResultSet rs = dbConnection.st.executeQuery(sql)) {
                return rs.next();
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
}
