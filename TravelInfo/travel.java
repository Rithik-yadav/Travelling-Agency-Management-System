package TravelInfo;

import DataBase.dbConnection;
import java.sql.*;
import java.util.*;
import VehicleArray.Validation;

public class travel {

    public static void addTravelInfo() {
        System.out.println("\n --------------ADD TRAVEL INFORMATION------------\n");
        Scanner scanner = new Scanner(System.in);

        try {
            String place = Validation.validateInput(scanner, "place", "Invalid place. Should contain only alphabets and spaces, with a minimum length of 4.");
            String vehicle_no = Validation.validateVehicleNumber(scanner);
            String d_name = Validation.validateInput(scanner, "driver name", "Invalid driver name. Should contain only alphabets and spaces, with a minimum length of 4.");
            String d_phone = Validation.validatePhoneNumber(scanner);

            String sql = "INSERT INTO travel_info(v_no, driver_name, driver_phone, travel_date, place) VALUES ('"
                    + vehicle_no + "','" + d_name + "','" + d_phone + "',CURRENT_DATE,'" + place + "')";
            int rowsAffected = dbConnection.st.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Travel Info Added Successfully!!");
            }
        } catch (SQLException ex) {
            System.err.println("Error executing SQL query: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("An unexpected error occurred: " + ex.getMessage());
        }
    }
    public static void displayTravelInfoForVehicle() {
        System.out.println("\n --------------DISPLAY TRAVEL INFORMATION------------\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Vehicle Number whose details you want to show:");
        String vehicleNumber = scanner.nextLine();
        try {
            ResultSet rs = dbConnection.st
                    .executeQuery("SELECT * FROM travel_info WHERE v_no = '" + vehicleNumber + "' ");
            boolean found = false;
            while (rs.next()) {
                String travelDate = rs.getString("travel_date");
                String driverName = rs.getString("driver_name");
                String driverPhone = rs.getString("driver_phone");
                String place = rs.getString("place");
                String travelInfo = travelDate + ": " + place + "\n" + driverName + " - " + driverPhone;
                System.out.println(travelInfo);
                found = true;
            }
            if (!found) {
                System.out.println("No travel information found for the given vehicle number.");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

}