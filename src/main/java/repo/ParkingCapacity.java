package repo;

public class ParkingCapacity {
    private String parkingName;
    private int capacity;


    public ParkingCapacity(String parking, int capacity) {
        this.parkingName = parking;
        this.capacity = capacity;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
