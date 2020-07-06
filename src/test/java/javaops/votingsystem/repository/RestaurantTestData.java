package javaops.votingsystem.repository;

import javaops.votingsystem.TestMatcher;
import javaops.votingsystem.model.Restaurant;

import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "menus");
    public static final int RESTAURANT1_ID = START_SEQ;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Mafia");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Pesto Cafe");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Murakami");
    public static final List<Restaurant> RESTAURANT_WITH_MENU_FOR_TODAY = List.of(RESTAURANT1);
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }

    public static Restaurant getNotExisting() {
        return new Restaurant(RESTAURANT1_ID + 10, "NotExisting restaurant");
    }
}
