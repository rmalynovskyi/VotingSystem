package javaops.votingsystem.web;

import javaops.votingsystem.model.Restaurant;
import javaops.votingsystem.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static javaops.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";
    private final RestaurantRepository restaurantRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public ProfileRestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}/menutoday")
    public Restaurant getWithDayMenu(@PathVariable int id) {
        log.info("get restaurant {} with menu for today", id);
        return checkNotFoundWithId(restaurantRepository.getWithDayMenu(id), id);
    }

    @GetMapping("/menutoday")
    public List<Restaurant> getAllWithMenuOfDay() {
        log.info("get all restaurants with menu for today");
        return restaurantRepository.getAllWithDayMenu();
    }
}
