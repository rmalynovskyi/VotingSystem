package javaops.votingsystem.repository;

import javaops.votingsystem.TestMatcher;
import javaops.votingsystem.model.Dish;

import java.util.Arrays;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsComparator(Dish.class, "menu");
    public static final int DISH1_ID = START_SEQ + 9;
    public static final Dish DISH1 = new Dish(DISH1_ID, "Double Fila", 199);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Super Fila XL", 299);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Super Fila XXL", 599);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Carbonara", 175);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Chicken Milaneze", 148);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Pork ribs", 270);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Potatos free", 120);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Fish file", 290);

    public static final List<Dish> DISHES_MENU1 = Arrays.asList(DISH1, DISH2);
    public static final List<Dish> DISHES_MENU2 = Arrays.asList(DISH4, DISH3);
    public static final List<Dish> DISHES_MENU3 = Arrays.asList(DISH5, DISH6);
    public static final List<Dish> DISHES_MENU4 = Arrays.asList(DISH8, DISH7);

    public static Dish getNew() {
        return new Dish(null, "New Dish", 200);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated", 77);
    }
}
