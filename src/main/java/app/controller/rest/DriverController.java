package app.controller.rest;

import app.model.Client;
import app.model.Driver;
import app.model.DriverStatus;
import app.model.Order;
import app.service.DriverService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private DriverService driverService = ServiceSinglePointAccess.getDriverService();

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.status(HttpStatus.OK).body(driverService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(driverService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.status(HttpStatus.OK).body(driverService.save(driver));
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseEntity<Driver> updateDriverStatus(@PathVariable Integer id, @PathVariable DriverStatus status) {
        driverService.updateDriverStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(driverService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Driver> update(@RequestBody Driver driver) {
        Driver driverFromDB = driverService.findById(driver.getId());
        driverFromDB.setName(driverFromDB.getName());
        driverFromDB.setPhone(driver.getPhone());
        driverService.update(driverFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(driverFromDB);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDriver(@RequestBody Driver driver) {
        driverService.delete(driver);
        return ResponseEntity.status(HttpStatus.OK).body("Driver deleted");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriverById(@PathVariable Integer id) {
        Driver driver = driverService.findById(id);
        driverService.delete(driver);
        return ResponseEntity.status(HttpStatus.OK).body("Driver with id " + driver.getId() + " was deleted");
    }
}
