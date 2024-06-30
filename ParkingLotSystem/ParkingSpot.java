
import java.util.Random;


enum ParkingSpotType {
    CarSpot,
    ElectricCarSpot,
    MotorCycleSpot,
}

abstract class ParkingSpot {

    private int spotId;
    private String vehicleNumber; // we can also create a new vehicle class for storing vehicle details
    private boolean isAvailable;
    private final int hourlyPrice;
    private final ParkingSpotType parkingSpotType;
    private static final Random random = new Random();

    public ParkingSpot(ParkingSpotType spotType, int hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
        this.parkingSpotType = spotType;
        this.isAvailable = true;
        this.vehicleNumber = null;
        this.spotId = generateUniqueId();
    }

    public int getHourlyPrice() {
        return this.hourlyPrice;
    }

    public int getSpotId() {
        return this.spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getVehicleDetails() {
        return this.vehicleNumber;
    }

    public ParkingSpotType getParkingSpotType() {
        return this.parkingSpotType;
    }

    public boolean isSpotAvailable() {
        return this.isAvailable;
    }

    public void assignVehicleToSpot(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        this.isAvailable = false;
    }
    
    public void unassignVehicleFromSpot() {
        this.vehicleNumber = null;
        this.isAvailable = true;
    }

    public static int generateUniqueId() {
        long currentTimeMillis = System.currentTimeMillis();
        int timeComponent = (int) (currentTimeMillis % 1000);
        int randomComponent = random.nextInt(1000);
        int uniqueId = (timeComponent * 1000) + randomComponent;
        uniqueId = uniqueId % 1000000;
        if (uniqueId < 100000) {
            uniqueId += 100000;
        }
        return uniqueId;
    }


    @Override
    public String toString() {
        return this.getSpotId() + " " + this.getParkingSpotType();
    }
}
