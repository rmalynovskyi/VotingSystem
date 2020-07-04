package javaops.votingsystem.repository;

import javaops.votingsystem.model.Restaurant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Mafia");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Pesto Cafe");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Murakami");
    public static final List<Restaurant> RESTAURANT_WITH_MENU_FOR_TODAY = Collections.singletonList(RESTAURANT1);
    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }

    public static Restaurant getNotExisting() {
        return new Restaurant(RESTAURANT1_ID + 10, "NotExisting restaurant");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menus");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menus").isEqualTo(expected);
    }
}
