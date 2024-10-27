package app.service.implementation;

import app.model.Restaurant;
import app.repository.RestaurantRepository;
import app.service.RestaurantService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository = RepositorySinglePointAccess.getRestaurantRepository();

    @Override
    public Restaurant findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }

    @Override
    public boolean delete(Restaurant restaurant) {
        return restaurantRepository.delete(restaurant);
    }

    @Override
    public Restaurant findById(Integer id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
