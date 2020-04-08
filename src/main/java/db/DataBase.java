package db;

import repo.ParkingCapacity;
import repo.ParkingCar;

import java.sql.*;
import java.util.LinkedList;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/thoughtworks" +
            "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong";
    private static final String ADMIN = "root";
    private static final String ADMIN_KEY = "mysql";

    public static void modifyParkingCapacity(String parking, int capacity) {
        String sql = "REPLACE INTO parking_capacity " +
                "(parking, capacity)" +
                "VALUES" +
                "(?,?)";
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, parking);
            statement.setInt(2, capacity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearParkingCapacity() {
        String sql = "DELETE FROM parking_capacity";
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<ParkingCapacity> queryParkingCapacity() {
        String sql = "SELECT parking, capacity FROM parking_capacity";
        LinkedList<ParkingCapacity> parkingCapacities = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parkingCapacities.add(new ParkingCapacity(
                        resultSet.getString("parking"),
                        resultSet.getInt("capacity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingCapacities;
    }

    public static LinkedList<ParkingCar> queryParkingCar(String parkingName) {
        String formatParkingName = formatParkingName(parkingName);
//        String sql = "SELECT no, car FROM ?";
        String sql = String.format("SELECT no, car FROM %s", formatParkingName);
        LinkedList<ParkingCar> parkingCars = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, formatParkingName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                parkingCars.add(new ParkingCar(
                        resultSet.getInt("no"),
                        resultSet.getString("car")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingCars;
    }

    public static void clearParking(String parkingName) {
        String formatParkingName = formatParkingName(parkingName);
//        String sql = "DELETE FROM ?";
        String sql = String.format("DELETE FROM %s", formatParkingName);
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
        PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, formatParkingName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyParking(String parkingName, int no, String car) {
        String formatParkingName = formatParkingName(parkingName);
//        String sql = "INSERT INTO ? (no, car) VALUE (?, ?)";
        String sql = String.format("INSERT INTO %s (no, car) VALUE (?, ?)", formatParkingName);
        try (Connection connection = DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
        PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, formatParkingName);
            statement.setInt(1, no);
            statement.setString(2, car);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeParkingCar(String parkingName, int no, String car) {
        String formatParkingName = formatParkingName(parkingName);
        String sql = String.format("DELETE FROM %s WHERE no = ? AND car = ?", formatParkingName);
        try (Connection connection =DriverManager.getConnection(URL, ADMIN, ADMIN_KEY);
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, no);
            statement.setString(2, car);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String formatParkingName (String parkingName) {
        return "parking_" + parkingName.toLowerCase();
    }

}
