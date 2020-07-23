package javaops.votingsystem.web;

import javaops.votingsystem.model.Menu;
import javaops.votingsystem.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static javaops.votingsystem.util.ValidationUtil.*;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/menus";
    private final MenuRepository menuRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get menu {} for restaurant {}", id, restaurantId);
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    @GetMapping("/{id}/with")
    public Menu getWithDishes(@PathVariable int restaurantId, @PathVariable int id, @RequestParam String dishes) {
        log.info("get menu {} with dishes for restaurant {}", id, restaurantId);
        return checkNotFoundWithId(menuRepository.getWithDishes(id, restaurantId), id);
    }

    @GetMapping("/today")
    public Menu getWithDishesForToday(@PathVariable int restaurantId, @RequestParam String today) {
        log.info("get menu with dishes for today in restaurant {}", restaurantId);
        return checkNotFound(menuRepository.getWithDishesForToday(restaurantId), "localDate " + LocalDate.now());
    }
    
    @GetMapping
    public List<Menu> getAll(@PathVariable int restaurantId) {
        log.info("get all menus for restaurant {}", restaurantId);
        return menuRepository.getAll(restaurantId);
    }

    @GetMapping("/with")
    public List<Menu> getAllWithDishes(@PathVariable int restaurantId, @RequestParam String dishes) {
        log.info("get all menus with dishes for restaurant {}", restaurantId);
        return menuRepository.getAllWithDishes(restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete menu {} for restaurant {}", id, restaurantId);
        checkNotFoundWithId(menuRepository.delete(id, restaurantId), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(menu, id);
        log.info("update menu {} for restaurant {}", id, restaurantId);
        checkNotFound(menuRepository.save(menu, restaurantId), "id " + id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu, @PathVariable int restaurantId, BindingResult bindingResult) {
        checkNew(menu);
        //log.info(bindingResult.getFieldError().getField());
        log.info("create menu {} for restaurant {}", menu, restaurantId);
        Menu created = menuRepository.save(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
