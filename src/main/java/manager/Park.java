package manager;

import db.DataBase;
import exception.ParkingLotFullException;
import repo.ParkingCar;
import repo.ParkingLot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Park extends ParkManager{

    public Park() {
        this.loadLastDataFromDataBase();
    }

    public String toPark(String car) {
        Collections.sort(this.parkingLots);
        String parkingName = "";
        int no = -1;
        for (ParkingLot parkingLot : this.parkingLots) {
            if (parkingLot.getParkingCars().size() < parkingLot.getCapacity()) {
                no = this.parkCarNo(parkingLot);
                parkingName = parkingLot.getName();
                DataBase.modifyParking(parkingName, no, car);
                break;
            }
        }
        if (no == -1) {
            throw new ParkingLotFullException();
        }
        return String.format("%s,%s,%s", parkingName, no, car);
    }

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
