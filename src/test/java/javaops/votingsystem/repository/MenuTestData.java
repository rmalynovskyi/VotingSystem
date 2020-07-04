package javaops.votingsystem.repository;

import javaops.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
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

    public static final List<Menu> MENUS_RESTAURANT2 = Arrays.asList(MENU3, MENU4);
    public static final List<Menu> MENUS_RESTAURANT3 = Arrays.asList(MENU6, MENU5);

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "restaurant").isEqualTo(expected);
    }
}
