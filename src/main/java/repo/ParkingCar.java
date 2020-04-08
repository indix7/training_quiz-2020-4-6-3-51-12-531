package repo;

public class ParkingCar implements Comparable<ParkingCar> {
    private int no;
    private String Car;

    public ParkingCar(int no, String car) {
        this.no = no;
        this.Car = car;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCar() {
        return Car;
    }

    public void setCar(String car) {
        Car = car;
    }

    @Override
    public int compareTo(ParkingCar o) {
        return this.no - o.getNo();
    }
}
