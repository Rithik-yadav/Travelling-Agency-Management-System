import Login.*;
import DriverTextFile.Driver;
import VehicleArray.Vehicle;
import VehicleArray.LoadFunction;
import Signup.*;
import TravelInfo.*;
import DataBase.dbConnection;
import Category.category;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FleetManagementApp {
    private void displayMenu() {
        try {
            if (LoginConsole.login()) {
                System.out.println("Login successful. Welcome to the Fleet Management System!");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println();
                    System.err.println("-----------------------------------------------------------");
                    System.out.println("Menu:");
                    System.out.println("1. Display Travelling Details");
                    System.out.println("2. Add Travelling Details");
                    System.out.println("3. Add New Driver");
                    System.out.println("4. Display All Drivers");
                    System.out.println("5. Delete Driver");
                    System.out.println("6. Add New Vehicle Category");
                    System.out.println("7. Display Vehicle Categories");
                    System.out.println("8. Delete Category");
                    System.out.println("9. Add New Vehicle");
                    System.out.println("10. Display Vehicles");
                    System.out.println("11. Delete Vehicle");
                    System.out.println("12. Update Vehicle Driver");
                    System.out.println("13. Logout");
                    System.out.println("14. SignUp");
                    System.out.println("15. Exit Application");

                    boolean validInput = false;
                    int choice = 0;

                    do {
                        System.out.print("Enter your choice: ");
                        String input = scanner.nextLine();

                        try {
                            choice = Integer.parseInt(input);
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid input. Please enter a valid number.");
                        }
                    } while (!validInput);

                    switch (choice) {
                        case 1:
                            travel.displayTravelInfoForVehicle();
                            break;
                        case 2:
                            travel.addTravelInfo();
                            break;
                        case 3:
                            Driver.addDriverInfo();
                            break;
                        case 4:
                            Driver.displayDriverDetails();
                            break;
                        case 5:
                            Driver.deleteDriver();
                            break;
                        case 6:
                            category.insertCategory();
                            break;
                        case 7:
                            category.displayCategoryData();
                            break;
                        case 8:
                            category.deleteCategory();
                            break;
                        case 9:
                            Vehicle.addVehicle();
                            break;
                        case 10:
                            Vehicle.displayVehicles();
                            break;
                        case 11:
                            Vehicle.deleteVehicle();
                            break;
                        case 12:
                            Vehicle.updateVehicleDriver();
                            break;
                        case 13:
                            System.out.println("Logging out.");
                            return;
                        case 14:
                            System.out.println("Sign Up page:");
                            signup.signupp();
                            break;
                        case 15:
                            System.out.println("Exiting the program.");
                            dbConnection.c.close();
                            dbConnection.st.close();
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            validInput = false;
                            break;
                    }
                }
            } else {
                System.out.println("Login Failed!! \nExiting the program.");
            }
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        LoadFunction.addDummyVehicles();
        FleetManagementApp app = new FleetManagementApp();
        app.displayMenu();
    }
}
