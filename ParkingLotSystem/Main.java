
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(4, 2, 2);
        int exit;
        do {
            System.out.println("Enter Gate Type, 1. Entry Gate, 2. Exit Gate");
            int gateType = sc.nextInt();
            System.out.println("Enter Gate Number 1 or 2");
            int gateId = sc.nextInt();
            if (gateType == 1) {
                System.out.println("Enter vehicle number: ");
                String vehicleNumber = sc.next();
                System.out.println("Enter vehicle spot: 1. Car spot, 2. Electric car spot, 3. Bike Spot");
                int spotType = sc.nextInt();
                parkingLotSystem.assignVehicleToSpot(vehicleNumber, spotType, gateId); 
            }
            else{
                System.out.println("Enter parking spot id");
                int spotId = sc.nextInt();
                parkingLotSystem.unassignVehicleFromSpot(spotId); 
                // todo: add payment class which accept different type of payment options
            }
            System.out.println("Do you want to exit, 1 or 0");
            exit = sc.nextInt();
        } while (exit == 0);
    }
}
