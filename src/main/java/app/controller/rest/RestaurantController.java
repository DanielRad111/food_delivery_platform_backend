package app.controller.rest;

import app.model.Restaurant;
import app.service.RestaurantService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService = ServiceSinglePointAccess.getRestaurantService();

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.save(restaurant));
    }

    @PutMapping("/update")
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant){
        Restaurant restaurantFromDB = restaurantService.findById(restaurant.getId());
        restaurantFromDB.setName(restaurant.getName());
        restaurantFromDB.setAddress(restaurant.getAddress());
        restaurantService.update(restaurantFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantFromDB);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Restaurant restaurant){
        restaurantService.delete(restaurant);
        return ResponseEntity.status(HttpStatus.OK).body("Restaurant deleted");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        Restaurant restaurant = restaurantService.findById(id);
        restaurantService.delete(restaurant);
        return ResponseEntity.status(HttpStatus.OK).body("Restaurant with id " + restaurant.getId() + " was deleted");
    }
}
