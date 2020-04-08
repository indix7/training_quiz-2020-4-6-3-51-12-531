package manager;

import db.DataBase;
import repo.ParkingCapacity;
import repo.ParkingCar;
import repo.ParkingLot;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParkManager {
    protected ArrayList<ParkingLot> parkingLots = new ArrayList<>();

    protected void loadLastDataFromDataBase() {
        LinkedList<ParkingCapacity> parkingCapacities = DataBase.queryParkingCapacity();
        for (ParkingCapacity parkingCapacity: parkingCapacities) {
            String parkingName = parkingCapacity.getParkingName();
            int capacity = parkingCapacity.getCapacity();
            LinkedList<ParkingCar> parkingCars = DataBase.queryParkingCar(parkingName);
            this.parkingLots.add(new ParkingLot(parkingName, parkingCars, capacity));
        }
    }
}
