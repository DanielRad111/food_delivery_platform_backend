package app.repository;

import app.model.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CRUDRepository<Restaurant, Integer>{
    Restaurant findByName(String name);
}
