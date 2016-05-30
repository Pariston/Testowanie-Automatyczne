package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;

public class CarManager {

    private Connection connection;

    private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
    private static final String CREATE_TABLE_CAR = "CREATE TABLE Car(c_id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, model varchar(20), yop integer, owner_id bigint FOREIGN KEY references Person(p_id))";
    private static final String DROP_TABLE_CAR = "DROP TABLE Car";

    private PreparedStatement addCarStmt;
    private PreparedStatement sellCarStmt;
    private PreparedStatement getCarWithOwnerStmt;
    private PreparedStatement getAllCarsStmt;
    private PreparedStatement getCarByIdStmt;
    private PreparedStatement removeCarStmt;
    private PreparedStatement deleteAllCarsStmt;

    private PreparedStatement dropTable;
    private Statement statement;

    public CarManager() {
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;

            rs = connection.getMetaData().getTables(null, null, null, null);
            tableExists = false;
            while (rs.next()) {
                if ("Car".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }

            if (!tableExists)
                statement.executeUpdate(CREATE_TABLE_CAR);

            addCarStmt = connection.prepareStatement(
                    "INSERT INTO Car (model, yop) VALUES (?, ?)");
            sellCarStmt = connection.prepareStatement(
                    "UPDATE Car SET owner_id = ? WHERE c_id = ?");
            deleteAllCarsStmt = connection.prepareStatement(
                    "DELETE FROM Car");
            removeCarStmt = connection.prepareStatement(
                    "DELETE FROM Car where c_id = ?");
            getCarWithOwnerStmt = connection.prepareStatement(
                    "SELECT p_id, name, yob, c_id, model, yop, owner_id FROM Person JOIN Car ON c_id = ?");
            getCarByIdStmt = connection.prepareStatement(
                    "SELECT * FROM Car where c_id = ?");
            getAllCarsStmt = connection.prepareStatement(
                    "SELECT * FROM Car");
            dropTable = connection.prepareStatement(
                    "DROP TABLE Car"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        return connection;
    }

    public void clearCars() {
        try {
            deleteAllCarsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addCar(Car car) {
        int count = 0;
        try {
            addCarStmt.setString(1, car.getModel());
            addCarStmt.setInt(2, car.getYop());

            count = addCarStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void dropTable() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;

            rs = connection.getMetaData().getTables(null, null, null, null);
            tableExists = false;
            while (rs.next()) {
                if ("Car".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }
            if (tableExists)
                dropTable.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {

        List<Car> cars = new ArrayList<Car>();
        PersonManager pm = new PersonManager();

        try {
            ResultSet rs = getAllCarsStmt.executeQuery();

            while (rs.next()) {
                Car c = new Car();
                Person p = pm.getPerson(rs.getLong("owner_id"));
                c.setId(rs.getLong("c_id"));
                c.setModel(rs.getString("model"));
                c.setYop(rs.getInt("yop"));
                c.setOwner(p);
                cars.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public int removeCar(Car car) {
        int count = 0;
        try {
            removeCarStmt.setLong(1, car.getId());
            count = removeCarStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Car getCarWithOwner(Car car) {

        Car c = new Car();
        try {

            getCarWithOwnerStmt.setLong(1, car.getId());
            ResultSet rs = getCarWithOwnerStmt.executeQuery();

            while (rs.next()) {

                Person p = new Person();

                p.setId(rs.getInt("p_id"));
                p.setFirstName(rs.getString("name"));
                p.setYob(rs.getInt("yob"));

                c.setId(rs.getInt("c_id"));
                c.setModel(rs.getString("model"));
                c.setYop(rs.getInt("yop"));

                c.setOwner(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public int sellCar(Car car, Person person) {
        int count = 0;
        try {

            sellCarStmt.setLong(1, person.getId());
            sellCarStmt.setLong(2, car.getId());


            count = sellCarStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Car getCar(Long id) {
        Car c = new Car();
        PersonManager pm = new PersonManager();
        try {
            getCarByIdStmt.setLong(1, id);
            ResultSet rs = getCarByIdStmt.executeQuery();

            while (rs.next()) {
                Person p = pm.getPerson(rs.getLong("owner_id"));

                c.setId(rs.getInt("c_id"));
                c.setModel(rs.getString("model"));
                c.setYop(rs.getInt("yop"));
                c.setOwner(p);
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
}
