
class Receipt {

    public static void printReceipt(ParkingSpot parkingSpot, EntryGate entryGate, ParkingFloor parkingFloor) {
        System.out.println("Your vehicle is assigned successfully");
        System.out.println("Parking Spot Id: " + parkingSpot.getSpotId());
        System.out.println("Floor No: " + parkingFloor.getParkingFloor());
        System.out.println("Spot Type: " + parkingSpot.getParkingSpotType());
        System.out.println("Vehicle Number: " + parkingSpot.getVehicleDetails());
        System.out.println("Hourly Parking Price: " + parkingSpot.getHourlyPrice());
    }
}
