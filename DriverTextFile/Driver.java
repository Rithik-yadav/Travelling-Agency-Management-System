package DriverTextFile;

import java.io.*;
import java.nio.file.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import VehicleArray.Validation;


public class Driver {
    public static final String FILE_PATH = "driver_data.txt";
    public static void addDriverInfo() {
        System.out.println("\n --------------ADD DRIVER INFORMATION--------------\n");

        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            name = null;
            try {
                name = Validation.validateInput(scanner, "driver name", "Invalid driver name. Should contain only alphabets and spaces, with a minimum length of 4.");
                if (Validation.isDuplicateName(name)) {
                    throw new InputMismatchException("Driver with this name already exists.");
                }
                if (name.length() < 3) {
                    throw new InputMismatchException("Name should be at least 3 characters long.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (Validation.isDuplicateName(name));

        String phone = Validation.validatePhoneNumber(scanner);
        String address = "";
        while (address.trim().isEmpty() || address.length() < 5 || !Validation.isValidAddress(address)) {
            try {
                System.out.print("Enter driver's address: ");
                address = scanner.nextLine().trim();
                if (address.isEmpty()) {
                    throw new InputMismatchException("Address cannot be empty.");
                }
                if (address.length() < 5) {
                    throw new InputMismatchException("Address should be at least 5 characters long.");
                }
                if (!Validation.isValidAddress(address)) {
                    throw new InputMismatchException("Invalid address. Please enter a valid combination of special characters, numbers, and alphabets.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        String lic = "";
        while (!Validation.isValidLicenseNumber(lic) || lic.length() < 5) {
            try {
                System.out.print("Enter driver's license number: ");
                lic = scanner.nextLine().trim();
                if (lic.isEmpty()) {
                    throw new InputMismatchException("Address cannot be empty.");
                }
                if (!Validation.isValidLicenseNumber(lic)) {
                    throw new InputMismatchException("Invalid license number. Please enter a valid license number.");
                }
                if (lic.length() < 5) {
                    throw new InputMismatchException("License number should be at least 5 characters long.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.printf("%s:%s:%s:%s%n", name, phone, address, lic);
            System.out.println("Driver Added Successfully!!");
        } catch (IOException e) {
            System.err.println("Error adding driver: " + e.getMessage());
        }
    }
    public static void displayDriverDetails() {
        System.out.println("\n --------------DISPLAY DRIVER DETAILS--------------\n");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            int totalDrivers = 0;

            System.out.println("Driver Details:");
            System.out.printf("%-15s %-30s %-20s %-40s%n", "Phone", "Name", "License Number", "Address");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4) {
                    String p = parts[1];
                    String n = parts[0];
                    String a = parts[2];
                    String l = parts[3];

                    System.out.printf("%-15s %-30s %-20s %-40s%n", p, n, l, a);
                    totalDrivers++;
                }
            }

            System.out.println("Total Drivers: " + totalDrivers);
        } catch (IOException e) {
            System.err.println("Error displaying driver details: " + e.getMessage());
        }
    }
    public static void deleteDriver() {
        System.out.println("\n --------------DELETE DRIVER--------------\n");
        Scanner scanner = new Scanner(System.in);

        String driverName = "";
        while (driverName.trim().isEmpty()) {
            System.out.print("Enter the Driver name to delete: ");
            driverName = scanner.nextLine().trim();
            if (driverName.isEmpty()) {
                System.out.println("Error: Driver name cannot be empty.");
            }
        }
        String lowercaseDriverName = driverName.toLowerCase();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
             PrintWriter writer = new PrintWriter(new FileWriter("temp.txt"))) {
            boolean deleted = false;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4 && parts[0].equalsIgnoreCase(lowercaseDriverName)) {
                    deleted = true;
                } else {
                    writer.println(line);
                }
            }
            if (!deleted) {
                System.out.println("Driver not found.");
            } else {
                System.out.println("Driver Deleted Successfully!!");
            }
        } catch (IOException e) {
            System.err.println("Error deleting driver: " + e.getMessage());
        }
        Path tempPath = Paths.get("temp.txt");
        Path originalPath = Paths.get(FILE_PATH);
        try {
            Files.move(tempPath, originalPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error updating driver data: " + e.getMessage());
        }
    }
}
