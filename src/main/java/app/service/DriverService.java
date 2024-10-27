package app.service;

import app.model.Driver;
import app.model.DriverStatus;

import java.util.List;

public interface DriverService {
    Driver save(Driver driver);
    Driver findById(Integer id);
    Driver findByName(String name);
    void updateDriverStatus(Integer id, DriverStatus newStatus);
    void update(Driver driver);
    List<Driver> findAll();
    boolean delete(Driver driver);
    List<Driver> findFreeDrivers();
}
