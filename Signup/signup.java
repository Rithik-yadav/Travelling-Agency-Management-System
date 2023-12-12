package Signup;

import DataBase.dbConnection;
import java.sql.SQLException;
import java.util.Scanner;
import VehicleArray.Validation;

public class signup {

    public static void signupp() {
        System.out.println("\n --------------SIGNUP PAGE--------------\n");
        Scanner scanner = new Scanner(System.in);
        String username = Validation.validateLengthInput(scanner, "Login ID you want to create:", "Invalid username. Should have a minimum length of 6.");
        String password = Validation.validateLengthInput(scanner, "password", "Invalid password. Should have a minimum length of 6.");
        try {
            String sql = "INSERT INTO login VALUES ('" + username + "','" + password + "')";
            int rowsAffected = dbConnection.st.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("User Created Successfully!!");
                System.out.println("Signup Success");
            }
            else{
                System.out.println("User Already Exists");
            }
        }
        catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());

        }
    }

}
