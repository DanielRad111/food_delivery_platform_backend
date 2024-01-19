package app.service.implementation;

import app.model.Driver;
import app.model.DriverStatus;
import app.repository.DriverRepository;
import app.service.DriverService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository = RepositorySinglePointAccess.getDriverRepository();

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void update(Driver driver) {
        driverRepository.update(driver);
    }

    @Override
    public boolean delete(Driver driver) {
        return driverRepository.delete(driver);
    }

    @Override
    public Driver findById(Integer id){
        return driverRepository.findById(id);
    }

    @Override
    public Driver findByName(String name){
        return driverRepository.findByName(name);
    }

    @Override
    public List<Driver> findAll(){
        return driverRepository.findAll();
    }

    @Override
    public void updateDriverStatus(Integer id, DriverStatus newStatus){
        driverRepository.updateDriverStatus(id, newStatus);
    }

    @Override
    public List<Driver> findFreeDrivers(){
        return driverRepository.findFreeDrivers();
    }
}
