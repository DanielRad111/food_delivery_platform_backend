package app.repository;

import app.model.Driver;
import app.model.DriverStatus;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends CRUDRepository<Driver, Integer>{
    List<Driver> findFreeDrivers();
    Driver findById(Integer id);
    void updateDriverStatus(Integer id, DriverStatus newStatus);
    Driver findByName(String name);
}
