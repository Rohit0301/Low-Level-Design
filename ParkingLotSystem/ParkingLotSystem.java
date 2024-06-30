
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParkingLotSystem {

    private final int TOTAL_FLOORS;
    private final int TOTAL_ENTRY_GATES;
    private final int TOTAL_EXIT_GATES;
    private final ArrayList<ParkingFloor> parkingFloors;
    private final Map<Integer, EntryGate> entryGates;
    private final Map<Integer, ExitGate> exitGates;

    public ParkingLotSystem(int totalFloors, int totalEntryGates, int totalExitGates) {
        this.TOTAL_FLOORS = totalFloors;
        this.TOTAL_ENTRY_GATES = totalEntryGates;
        this.TOTAL_EXIT_GATES = totalExitGates;
        parkingFloors = new ArrayList<>();
        entryGates = new HashMap<>();
        exitGates = new HashMap<>();
        initParkingLot();
    }

    public final void initParkingLot() {
        addParkingFloor();
        addEntryGates();
        addExitGates();
    }

    // Assuming each floors have 5 spots of each spot type, adding different kind spots on each floor
    public void addParkingFloor() {
        for (int floor = 0; floor < TOTAL_FLOORS; floor++) {
            ParkingFloor parkingFloor = new ParkingFloor(floor);
            for (int i = 0; i < 5; i++) {
                CarParkingSpot carParkingSpot = new CarParkingSpot();
                parkingFloor.addParkingSpot(carParkingSpot.getParkingSpotType(), carParkingSpot);
            }
            for (int i = 0; i < 5; i++) {
                MotorCycleParkingSpot motorCycleParkingSpot = new MotorCycleParkingSpot();
                parkingFloor.addParkingSpot(motorCycleParkingSpot.getParkingSpotType(), motorCycleParkingSpot);
            }
            for (int i = 0; i < 5; i++) {
                ElectricCarParkingSpot electricCarParkingSpot = new ElectricCarParkingSpot();
                parkingFloor.addParkingSpot(electricCarParkingSpot.getParkingSpotType(), electricCarParkingSpot);
            }
            this.parkingFloors.add(parkingFloor);
        }
    }

    // This function initiate default entry gate and assign unique to gate
    public void addEntryGates() {
        for (int gateId = 1; gateId <= TOTAL_ENTRY_GATES; gateId++) {
            EntryGate entryGate = new EntryGate(gateId);
            entryGates.put(gateId, entryGate);
        }
    }

    // This function initiate default exit gate and assign unique to gate
    public void addExitGates() {
        for (int gateId = 1; gateId <= TOTAL_EXIT_GATES; gateId++) {
            ExitGate exitGate = new ExitGate(gateId);
            exitGates.put(gateId, exitGate);
        }
    }

    // This function search the parking detials by parking spot id and unassing the vehicle for that spot
    public void unassignVehicleFromSpot(int spotId){
        ParkingSpot currentParkingSpots = null;
        for(int floor=0;floor<parkingFloors.size();floor++){
            currentParkingSpots = parkingFloors.get(floor).getParkingSpot(spotId);
            if(currentParkingSpots!=null) break;
        }
        if(currentParkingSpots ==null || currentParkingSpots.isSpotAvailable()==true){
            System.out.println("Invalid parking spot ID or spot is empty!");
            return;
        }
        String vehicleNumber = currentParkingSpots.getVehicleDetails();
        currentParkingSpots.unassignVehicleFromSpot();
        System.out.println("Vehicle unassign successfully: " + vehicleNumber);
    }

    // This function take vehicle details and spot type and find available spot for that vehicle type and assign vehcile at that position and print receipt
    public void assignVehicleToSpot(String vehicleNumber, int parkingSpot, int gateId) {
        if (entryGates.containsKey(gateId) == false) {
            System.out.println("Invalid Gate Id");
            return;
        }
        EntryGate entryGate = entryGates.get(gateId);
        ParkingSpotType spotType = switch (parkingSpot) {
            case 1 ->
                ParkingSpotType.CarSpot;
            case 2 ->
                ParkingSpotType.ElectricCarSpot;
            default ->
                ParkingSpotType.MotorCycleSpot;
        };
        boolean isVehicleAllocated = false;
        for (int i = 0; i < parkingFloors.size(); i++) {
            ArrayList<ParkingSpot> availableParkingSpots = parkingFloors.get(i).getAvailableParkingSpots(spotType);
            if (availableParkingSpots.isEmpty()) {
                continue;
            }
            ParkingSpot firstAvailableSlot = availableParkingSpots.get(0);
            firstAvailableSlot.assignVehicleToSpot(vehicleNumber);
            entryGate.printReceipt(firstAvailableSlot, parkingFloors.get(i));
            isVehicleAllocated = true;
            break;
        }
        if (!isVehicleAllocated) {
            System.out.println("There is no spot available for " + spotType);
        }
    }
}
