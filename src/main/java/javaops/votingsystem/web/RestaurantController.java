package javaops.votingsystem.web;

import javaops.votingsystem.model.Restaurant;
import javaops.votingsystem.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static javaops.votingsystem.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    static final String REST_URL = "/restaurants";
    private final RestaurantRepository restaurantRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @GetMapping("/{id}/with")
    public Restaurant getWithMenus(@PathVariable int id) {
        return checkNotFoundWithId(restaurantRepository.getWithMenus(id), id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @GetMapping("/menus")
    public List<Restaurant> getAllWithMenus() {
        return restaurantRepository.getAllWithMenus();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        checkNotFound(restaurantRepository.save(restaurant), "id " + id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
