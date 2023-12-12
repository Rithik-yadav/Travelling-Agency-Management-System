package VehicleArray;
import static VehicleArray.Vehicle.*;

import java.util.Scanner;

public class LoadFunction {
    public static void loadVehicleData(String vehicleNumber, String vehicleName, String category, String driverName) {
        try {
            if (vehicleCount < MAX_VEHICLES) {
                vehicles[vehicleCount][0] = vehicleNumber;
                vehicles[vehicleCount][1] = vehicleName;
                vehicles[vehicleCount][2] = category;
                vehicles[vehicleCount][3] = driverName;
                vehicleCount++;
            }
        } catch (RuntimeException e) {
            System.err.println("Error during loading vehicle data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error during loading vehicle data: " + e.getMessage());
        }
    }
    public static void addDummyVehicles() {
        loadVehicleData("DL01J7890", "Swift", "Sedan", "Anil");
        loadVehicleData("DL05F4321", "Maruti Ecco", "Heavy-duty", "Anil Khanna");
        loadVehicleData("DL11G8765", "Tata Safari", "SUV", "Kunal");
        loadVehicleData("HR02D3456", "Tata Sumo", "MUV", "Sharma");
        loadVehicleData("HR03B5678", "Tata Prima", "Heavy-duty", "Sunil Gupta");
        loadVehicleData("HR06C9012", "Mahindra Alfa Plus", "Mini-Truck", "Hemant");
        loadVehicleData("HR08E7890", "Maruti Ertiga", "MUV", "Preeti Verma");
        loadVehicleData("UP01Y1234", "Eicher Pro", "Truck", "Rahul Sharma");
        loadVehicleData("HR20AK4333", "Scorpio", "SUV", "Rithik Yadav");
    }
}
