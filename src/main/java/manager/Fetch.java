package manager;

import db.DataBase;
import exception.InvalidTicketException;
import repo.ParkingCar;
import repo.ParkingLot;

import static manager.ParkManager.parkingLots;

public class Fetch {
    private static final String EXCEPTIONMESSAGE = "很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！";

    public String toFetch(String ticket) {
        checkTicketLength(ticket);

        String parkingName = ticket.split(",")[0];
        int no = Integer.parseInt(ticket.split(",")[1]);
        String car = ticket.split(",")[2];

        ParkingLot parking = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getName().equals(parkingName))
                .findFirst()
                .orElseThrow(()->new InvalidTicketException(EXCEPTIONMESSAGE));


        for (ParkingCar parkingCar : parking.getParkingCars()) {
            if (parkingCar.getNo() == no && parkingCar.getCar().equals(car)){
                DataBase.removeParkingCar(parkingName,no, car);
                parking.removeCar(no, car);
                return car;
            }
        }
        throw new InvalidTicketException(EXCEPTIONMESSAGE);
    }

    private void checkTicketLength(String ticket) {
        if (ticket.split(",").length != 3) {
            throw new InvalidTicketException(EXCEPTIONMESSAGE);
        }
    }
}
