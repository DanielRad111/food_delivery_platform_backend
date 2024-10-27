package app.controller.rest;

import app.model.Menu;
import app.model.Restaurant;
import app.service.MenuService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private MenuService menuService = ServiceSinglePointAccess.getMenuService();

    @GetMapping("/all")
    public ResponseEntity<List<Menu>> findAllMenus() {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Menu> findMenuById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.save(menu));
    }

    @PutMapping("/update")
    public ResponseEntity<Menu> update(@RequestBody Menu menu){
        Menu menuFromDB = menuService.findById(menu.getId());
        menuFromDB.setProducts(menu.getProducts());
        menuService.update(menuFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(menuFromDB);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMenu(@RequestBody Menu menu) {
        menuService.delete(menu);
        return ResponseEntity.status(HttpStatus.OK).body("Menu deleted");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenuById(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        menuService.delete(menu);
        return ResponseEntity.status(HttpStatus.OK).body("Menu with id " + menu.getId() + " was deleted");
    }
}
