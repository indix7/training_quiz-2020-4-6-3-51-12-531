package manager;

import db.DataBase;
import exception.InvalidInitCommandException;
import repo.ParkingCar;
import repo.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class InitSystem extends ParkManager{

    public InitSystem() {
    }

    public void toInitSystem (String initParkingCapacity){
        DataBase.clearParkingCapacity();
        HashMap<String, Integer> parkingCapacityMap = normInitParkingCapacityString(initParkingCapacity);
        this.parkingLots.clear();
        for (Map.Entry<String, Integer> entry : parkingCapacityMap.entrySet()) {
            String parkingName = entry.getKey();
            int capacity = entry.getValue();
            DataBase.modifyParkingCapacity(parkingName, capacity);
            DataBase.clearParking(parkingName);
            parkingLots.add(new ParkingLot(parkingName, new LinkedList<ParkingCar>(),capacity));
        }
    }

    private HashMap<String, Integer> normInitParkingCapacityString(String initParkingCapacity) {
        HashMap<String, Integer> parkingCapacityMap = new HashMap<>();
        for (String parkingAndCapacity : initParkingCapacity.split(",")){
            if (parkingAndCapacity.split(":").length != 2) {
                throw new InvalidInitCommandException("传入参数非法");
            }
            String parkingName = parkingAndCapacity.split(":")[0];
            int capacity = Integer.parseInt(parkingAndCapacity.split(":")[1]);
            parkingCapacityMap.put(parkingName, capacity);
        }
        return parkingCapacityMap;
    }
}
