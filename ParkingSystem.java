import java.util.*;

public class ParkingSystem {

    static HashMap<String, Long> parkingDB = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    // Calculate Fee
    public static int calculateFee(long entryTime, long exitTime) {
        long duration = exitTime - entryTime;

        double hours = (double) duration / (1000 * 60 * 60);
        int roundedHours = (int) Math.ceil(hours);

        int ratePerHour = 20;
        return roundedHours * ratePerHour;
    }

    // Vehicle Entry
    public static void vehicleEntry() {
        System.out.print("Enter Vehicle Number: ");
        String vehicleNo = sc.nextLine().toUpperCase().trim();

        if (vehicleNo.isEmpty()) {
            System.out.println("Invalid vehicle number!");
            return;
        }

        if (parkingDB.containsKey(vehicleNo)) {
            System.out.println("Vehicle already parked!");
            return;
        }

        long entryTime = System.currentTimeMillis();
        parkingDB.put(vehicleNo, entryTime);

        System.out.println("Vehicle Entered Successfully!");
        System.out.println("Entry Time: " + new Date(entryTime));
    }

    // Vehicle Exit
    public static void vehicleExit() {
        System.out.print("Enter Vehicle Number: ");
        String vehicleNo = sc.nextLine().toUpperCase().trim();

        if (!parkingDB.containsKey(vehicleNo)) {
            System.out.println("Vehicle not found!");
            return;
        }

        long exitTime = System.currentTimeMillis();
        long entryTime = parkingDB.get(vehicleNo);

        int fee = calculateFee(entryTime, exitTime);

        System.out.println("\n--- Receipt ---");
        System.out.println("Vehicle No: " + vehicleNo);
        System.out.println("Entry Time: " + new Date(entryTime));
        System.out.println("Exit Time : " + new Date(exitTime));
        System.out.println("Parking Fee: ₹" + fee);

        parkingDB.remove(vehicleNo);
    }

    // Show Report
    public static void showReport() {
        System.out.println("\n--- Parking Report ---");
        System.out.println("Vehicles Currently Parked: " + parkingDB.size());

        if (parkingDB.isEmpty()) {
            System.out.println("No vehicles parked.");
        } else {
            for (String v : parkingDB.keySet()) {
                System.out.println(v + " -> Entered at " + new Date(parkingDB.get(v)));
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Parking System =====");
            System.out.println("1. Vehicle Entry");
            System.out.println("2. Vehicle Exit");
            System.out.println("3. Show Report");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    vehicleEntry();
                    break;
                case "2":
                    vehicleExit();
                    break;
                case "3":
                    showReport();
                    break;
                case "4":
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
