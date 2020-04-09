package manager;

import db.DataBase;
import repo.ParkingCapacity;
import repo.ParkingCar;
import repo.ParkingLot;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParkManager {
    protected static ArrayList<ParkingLot> parkingLots = new ArrayList<>();
    private Park park = new Park();
    private InitSystem initSystem = new InitSystem();
    private Fetch fetch = new Fetch();

    public ParkManager() {
        this.loadLastDataFromDataBase();
    }

    /**
     * 首次启动系统载入数据库中的原始数据
     */
    private void loadLastDataFromDataBase() {
        LinkedList<ParkingCapacity> parkingCapacities = DataBase.queryParkingCapacity();
        for (ParkingCapacity parkingCapacity: parkingCapacities) {
            String parkingName = parkingCapacity.getParkingName();
            int capacity = parkingCapacity.getCapacity();
            LinkedList<ParkingCar> parkingCars = DataBase.queryParkingCar(parkingName);
            parkingLots.add(new ParkingLot(parkingName, parkingCars, capacity));
        }
    }

    public String toParkCar(String car) {
        return park.toPark(car);
    }

    public void toInit(String initInfo) {
        initSystem.toInitSystem(initInfo);
    }

    public String toFetchCar(String ticket) {
        return fetch.toFetch(ticket);
    }
}
