package javaops.votingsystem.repository;

import javaops.votingsystem.TestMatcher;
import javaops.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator(Menu.class, "dishes", "restaurant");
    public static final int MENU1_ID = START_SEQ + 3;
    public static final Menu MENU1 = new Menu(MENU1_ID, LocalDate.now(), "Mafia menu");
    public static final Menu MENU2 = new Menu(MENU1_ID + 1, LocalDate.of(2020, 6, 20), "Mafia menu");
    public static final Menu MENU3 = new Menu(MENU1_ID + 2, LocalDate.of(2020, 6, 30), "Pesto Cafe menu");
    public static final Menu MENU4 = new Menu(MENU1_ID + 3, LocalDate.of(2020, 6, 20), "Pesto Cafe menu");
    public static final Menu MENU5 = new Menu(MENU1_ID + 4, LocalDate.of(2020, 6, 28), "Murakami menu");
    public static final Menu MENU6 = new Menu(MENU1_ID + 5, LocalDate.of(2020, 6, 30), "Murakami menu");

    public static Menu getNew() {
        return new Menu(null, LocalDate.of(2020, 7, 2), "New Menu");
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, LocalDate.of(2020, 7, 4), "Updated menu");
    }

    public static final List<Menu> MENUS_RESTAURANT1 = List.of(MENU1, MENU2);
    public static final List<Menu> MENUS_RESTAURANT2 = List.of(MENU3, MENU4);
    public static final List<Menu> MENUS_RESTAURANT3 = List.of(MENU6, MENU5);
}
