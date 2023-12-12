package VehicleArray;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import static VehicleArray.Validation.*;

public class Vehicle {
    protected static final int MAX_VEHICLES = 100;
    protected static final String[][] vehicles = new String[MAX_VEHICLES][4];
    protected static int vehicleCount = 0;

    public static void addVehicle() {
        try {
            if (vehicleCount < MAX_VEHICLES) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("\n --------------ADD NEW VEHICLE------------\n");
                vehicles[vehicleCount][1] = validateInput(scanner, "Vehicle Name", "Invalid place. Should contain only alphabets and spaces, with a minimum length of 2.");
                vehicles[vehicleCount][0] = validateVehicleNumber(scanner);
                vehicles[vehicleCount][2] = validateInput(scanner, "Category", "Invalid place. Should contain only alphabets and spaces, with a minimum length of 2.");
                vehicles[vehicleCount][3] = validateInput(scanner, "Driver Name", "Invalid place. Should contain only alphabets and spaces, with a minimum length of 2.");
                vehicleCount++;

                System.out.println("Vehicle Added Successfully!!");
            } else {
                System.out.println("Cannot add more vehicles. Limit reached.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter valid data.");
        } catch (RuntimeException e) {
            System.err.println("Validation error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during input: " + e.getMessage());
        }
    }
    public static void displayVehicles() {
        try {
            System.out.println("\n --------------Display VEHICLE INFORMATION WITH DRIVER------------\n");

            if (vehicleCount > 0) {
                System.out.println("vehicle Details:");
                System.out.printf("%-20s %-30s %-20s %-20s%n", "Vehicle no", "Name", "Category", "Driver Name");

                for (int i = 0; i < vehicleCount; i++) {
                    System.out.printf("%-20s %-30s %-20s %-20s%n",
                            vehicles[i][0], vehicles[i][1], vehicles[i][2], vehicles[i][3]);
                }
                System.out.println("Total Vehicles: " + vehicleCount);
            } else {
                System.out.println("No vehicles to display.");
            }
        } catch (Exception e) {
            System.err.println("Error during display: " + e.getMessage());
        }
    }
    public static void deleteVehicle() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the vehicle number to delete: ");
            String vehicleNoToDelete = scanner.nextLine().toLowerCase();
            int indexToDelete = -1;
            for (int i = 0; i < vehicleCount; i++) {
                if (vehicles[i][0].toLowerCase().equals(vehicleNoToDelete)) {
                    indexToDelete = i;
                    break;
                }
            }
            if (indexToDelete != -1) {
                for (int i = indexToDelete; i < vehicleCount - 1; i++) {
                    System.arraycopy(vehicles[i + 1], 0, vehicles[i], 0, vehicles[i + 1].length);
                }
                vehicleCount--;
                System.out.println("Vehicle Deleted Successfully!!");
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (Exception e) {
            System.err.println("Error during deletion: " + e.getMessage());
        }
    }
    public static void updateVehicleDriver() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the vehicle number to update: ");
            String vehicleNoToUpdate = scanner.nextLine().toLowerCase();
            int indexToUpdate = -1;
            for (int i = 0; i < vehicleCount; i++) {
                if (vehicles[i][0].toLowerCase().equals(vehicleNoToUpdate)) {
                    indexToUpdate = i;
                    break;
                }
            }
            if (indexToUpdate != -1) {
                System.out.print("Enter the Driver Name: ");
                vehicles[indexToUpdate][3] = scanner.nextLine();

                System.out.println("Success!!");
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (Exception e) {
            System.err.println("Error during update: " + e.getMessage());
        }
    }
}
