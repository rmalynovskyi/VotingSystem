package javaops.votingsystem.web;

import javaops.votingsystem.model.Dish;
import javaops.votingsystem.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/menus/{menuId}/dishes";
    private final DishRepository dishRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int menuId, @PathVariable int id) {
        log.info("get dish {} for menu {}", id, menuId);
        return dishRepository.get(id, menuId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId, @PathVariable int id) {
        dishRepository.delete(id, menuId);
    }

    @GetMapping
    public List<Dish> getAll(@PathVariable int menuId) {
        return dishRepository.getAll(menuId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int menuId) {
        dishRepository.save(dish, menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable int menuId) {
        Dish created = dishRepository.save(dish, menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
