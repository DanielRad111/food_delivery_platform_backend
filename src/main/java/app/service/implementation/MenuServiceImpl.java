package app.service.implementation;

import app.model.Menu;
import app.model.Restaurant;
import app.repository.MenuRepository;
import app.service.MenuService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository = RepositorySinglePointAccess.getMenuRepository();

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        return menuRepository.update(menu);
    }

    @Override
    public boolean delete(Menu menu) {
        return menuRepository.delete(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(Integer id){
        return menuRepository.findById(id);
    }

}
