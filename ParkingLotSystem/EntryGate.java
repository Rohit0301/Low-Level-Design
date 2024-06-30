
class EntryGate {

    private final int gateId;

    public EntryGate(int gateId) {
        this.gateId = gateId;
    }

    public int getEntryGateId() {
        return this.gateId;
    }

    public void printReceipt(ParkingSpot parkingSpot, ParkingFloor parkingFloor) {
        Receipt.printReceipt(parkingSpot, this, parkingFloor);
    }

}
