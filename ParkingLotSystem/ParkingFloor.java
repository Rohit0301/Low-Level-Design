
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParkingFloor {

    private final int floorNumer;
    private final Map<ParkingSpotType, ArrayList<ParkingSpot>> parkingSpots;

    public ParkingFloor(int floorNumer) {
        this.floorNumer = floorNumer;
        parkingSpots = new HashMap<>();
    }

    public int getParkingFloor() {
        return this.floorNumer;
    }

    // Add new parking spot to current floor 
    public void addParkingSpot(ParkingSpotType spotType, ParkingSpot parkingSpot) {
        if (parkingSpots.containsKey(spotType) == false) {
            parkingSpots.put(spotType, new ArrayList<>());
        }
        parkingSpots.get(spotType).add(parkingSpot);
    }

    // Return the parking spot details of given spotId
    public ParkingSpot getParkingSpot(int SpotId){
        for(ParkingSpotType spotType: parkingSpots.keySet()){
            for(ParkingSpot spot: parkingSpots.get(spotType)){
                if(spot.getSpotId()==SpotId) return spot;
            }
        }
        return null;
    }
    // Return all the parking spot of given parking spot type
    public ArrayList<ParkingSpot> getParkingSpots(ParkingSpotType spotType) {
        if (parkingSpots.containsKey(spotType)) {
            return this.parkingSpots.get(spotType);
        }
        return new ArrayList<>();
    }

    // Return all the available parking spot of given parking spot type
    public ArrayList<ParkingSpot> getAvailableParkingSpots(ParkingSpotType spotType) {
        ArrayList<ParkingSpot> availableParkingSpots = new ArrayList<>();
        if (parkingSpots.containsKey(spotType)) {
            for (ParkingSpot spot : parkingSpots.get(spotType)) {
                if (spot.isSpotAvailable()) {
                    availableParkingSpots.add(spot);
                }
            }
        }
        return availableParkingSpots;
    }

}
