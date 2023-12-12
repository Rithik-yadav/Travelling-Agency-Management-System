package VehicleArray;

import DriverTextFile.Driver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import DataBase.dbConnection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Validation {
    public static String validateInput(Scanner scanner, String inputType, String errorMessage) {
        String input;
        do {
            System.out.print("Enter " + inputType + ": ");
            try {
                input = scanner.nextLine().trim();
                if (!input.matches("^[a-zA-Z ]{2,}$")) {
                    throw new IllegalArgumentException(errorMessage);
                }
            } catch (Exception ex) {
                System.err.println("Validation error: " + ex.getMessage());
                input = "";
            }
        } while (input.isEmpty());
        return input;
    }
    public static String validateVehicleNumber(Scanner scanner) {
        String vehicleNumber;
        do {
            System.out.print("Enter vehicle number: ");
            try {
                vehicleNumber = scanner.nextLine().trim();
                if (!vehicleNumber.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$")) {
                    throw new IllegalArgumentException("Invalid vehicle number format. Should contain both alphabets and numbers, with a minimum length of 8.");
                }
            } catch (Exception ex) {
                System.err.println("Validation error: " + ex.getMessage());
                vehicleNumber = "";
            }
        } while (vehicleNumber.isEmpty());
        return vehicleNumber;
    }
    public static String validatePhoneNumber(Scanner scanner) {
        String phone;
        do {
            System.out.print("Enter driver phone Number: ");
            try {
                phone = scanner.nextLine().trim();
                if (!phone.matches("^[0-9]{10}$")) {
                    throw new IllegalArgumentException("Invalid phone number format. Should contain only digits and have a length of 10.");
                }
            } catch (Exception ex) {
                System.err.println("Validation error: " + ex.getMessage());
                phone = "";
            }
        } while (phone.isEmpty());
        return phone;
    }
    public static String validateLengthInput(Scanner scanner, String inputType, String errorMessage) {
        String input;
        do {
            System.out.print("Enter " + inputType + ": ");
            try {
                input = scanner.nextLine().trim();
                if (input.length() <= 5) {
                    throw new IllegalArgumentException(errorMessage);
                }
            } catch (Exception ex) {
                System.err.println("Validation error: " + ex.getMessage());
                input = "";
            }
        } while (input.isEmpty());
        return input;
    }

    //VALIDATIONS FOR THE DIRVER TEXT FILE
    public static boolean isDuplicateName(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Driver.FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 1 && parts[0].equalsIgnoreCase(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error checking duplicate name: " + e.getMessage());
        }
        return false;
    }
    public static boolean isValidAddress(String address) {
        return address.matches(".*[.,a-zA-Z0-9 ]+.*") && address.matches(".*[a-zA-Z].*");
    }
    public static boolean isValidLicenseNumber(String license) {
        return license.matches("^[a-zA-Z0-9]+$");
    }

    //duplicacy for category
    public static boolean isCategoryDuplicate(String category) throws SQLException {
        String query = "SELECT COUNT(*) FROM category WHERE name = '" + category + "'";
        ResultSet resultSet = dbConnection.st.executeQuery(query);
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
        return false;
    }
}
