package manager;

import db.DataBase;
import exception.InvalidInitCommandException;
import repo.ParkingCar;
import repo.ParkingLot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static manager.ParkManager.parkingLots;

public class InitSystem {

    public InitSystem() {
    }

    public void toInitSystem (String initParkingCapacity){
        DataBase.clearParkingCapacity();
        parkingLots.clear();
        HashMap<String, Integer> parkingCapacityMap = normInitParkingCapacityString(initParkingCapacity);

        for (Map.Entry<String, Integer> entry : parkingCapacityMap.entrySet()) {
            String parkingName = entry.getKey();
            int capacity = entry.getValue();

            DataBase.modifyParkingCapacity(parkingName, capacity);
            DataBase.clearParking(parkingName);

            parkingLots.add(new ParkingLot(parkingName, new LinkedList<ParkingCar>(),capacity));
        }
    }

    /**
     * 根据命令分割字符串，识别拥有多少停车场以及停车场容量
     * @param initParkingCapacity 传入的命令
     * @return 一个保存 含有多少个停车场以及这些停车场各自有多少容量的map
     */
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
