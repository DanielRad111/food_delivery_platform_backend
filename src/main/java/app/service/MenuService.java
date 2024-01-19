package app.service;

import app.model.Menu;
import app.model.Restaurant;

import java.util.List;

public interface MenuService {
    Menu save(Menu menu);
    Menu findById(Integer id);
    Menu update(Menu menu);
    boolean delete(Menu menu);
    List<Menu> findAll();
}
