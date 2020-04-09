package manager;

import db.DataBase;
import exception.ParkingLotFullException;
import repo.ParkingCar;
import repo.ParkingLot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static manager.ParkManager.parkingLots;

public class Park{

    public String toPark(String car) {
        Collections.sort(parkingLots);
        String parkingName = "";
        int no = -1;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkingCars().size() < parkingLot.getCapacity()) {
                no = this.parkCarNo(parkingLot);
                parkingName = parkingLot.getName();
                parkingLot.addCar(no, car);
                DataBase.modifyParking(parkingName, no, car);
                break;
            }
        }
        if (no == -1) {
            throw new ParkingLotFullException("非常抱歉，由于车位已满，暂时无法为您停车！");
        }
        return String.format("%s,%s,%s", parkingName, no, car);
    }

    /**
     * 分配编号
     * @param parkingLot 停车场
     * @return 最小的可用编号
     */
    private int parkCarNo(ParkingLot parkingLot) {
        LinkedList<ParkingCar> parkingCars = parkingLot.getParkingCars();
        Collections.sort(parkingCars);
        LinkedList<Integer> parkingNo = new LinkedList<>();
        for (ParkingCar parkingCar : parkingCars) {
            parkingNo.add(parkingCar.getNo());
        }
        return IntStream.rangeClosed(1, parkingLot.getCapacity())
                .filter(num->! parkingNo.contains(num))
                .findFirst()
                .orElse(-1);
    }
}
