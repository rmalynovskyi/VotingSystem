package javaops.votingsystem.repository;

import javaops.votingsystem.model.Restaurant;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Mafia");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Pesto Cafe");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Murakami");

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant");
    }
}
