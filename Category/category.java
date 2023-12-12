package Category;

import DataBase.dbConnection;
import VehicleArray.Validation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class category {
    public static void displayCategoryData() {
        try {
            System.out.println("\n --------------DISPLAY VEHICLE CATEGORY--------------\n");
            ResultSet rs = dbConnection.st.executeQuery("select * from category");
            int x = 0;
            System.out.println("Category Data:");
            System.out.printf("%-5s %-15s%n", "Index", "Name");
            while (rs.next()) {
                String n = rs.getString("name");
                x++;
                System.out.printf("%-5d %-15s%n", x, n);
            }
            System.out.println("Total Categories: " + x);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    public static void insertCategory() {
        try {
            System.out.println("\n --------------INSERT NEW VEHICLE CATEGORY--------------\n");

            Scanner scanner = new Scanner(System.in);
            String category = Validation.validateInput(scanner, "Category", "Invalid Category. Should contain only alphabets and spaces, with a minimum length of 2.");
            if (Validation.isCategoryDuplicate(category)) {
                System.out.println("Category already exists. Please choose a different category.");
                return;
            }
            String sql = "INSERT INTO category(name) VALUES ('" + category + "')";
            int rowsAffected = dbConnection.st.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Category added Successfully!!");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void deleteCategory() {
        System.out.println("\n --------------DELETE VEHICLE CATEGORY--------------\n");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the category name to delete: ");
        String categoryName = sc.nextLine();
        try {

            String deleteSql = "DELETE FROM category WHERE LOWER(name) = LOWER('" + categoryName + "')";
            int rowsAffected = dbConnection.st.executeUpdate(deleteSql);
            if (rowsAffected > 0) {
                System.out.println("Category Deleted Successfully!!");
            } else {
                System.out.println("Category not found.");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
