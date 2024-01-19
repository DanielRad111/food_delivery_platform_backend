package app.service;

import app.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant findByName(String name);
    Restaurant save(Restaurant restaurant);
    Restaurant findById(Integer id);
    Restaurant update(Restaurant restaurant);
    boolean delete(Restaurant restaurant);
    List<Restaurant> findAll();
}
