package repo;

import java.util.LinkedList;
import java.util.Objects;

public class ParkingLot implements Comparable<ParkingLot>{
    private String name;
    private LinkedList<ParkingCar> parkingCars;
    private int capacity;

    public ParkingLot(String name, LinkedList<ParkingCar> parkingCars, int capacity) {
        this.name = name;
        this.parkingCars = parkingCars;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public LinkedList<ParkingCar> getParkingCars() {
        return parkingCars;
    }

    public void setParkingCars(LinkedList<ParkingCar> parkingCars) {
        this.parkingCars = parkingCars;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addCar(int no , String car) {
        this.parkingCars.add(new ParkingCar(no, car));
    }

    public void removeCar(int no, String car) {
        this.parkingCars.removeIf(
                parkingCar -> parkingCar.getNo() == no && parkingCar.getCar().equals(car));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public int compareTo(ParkingLot o) {
        return this.name.compareTo(o.getName());
    }
}
