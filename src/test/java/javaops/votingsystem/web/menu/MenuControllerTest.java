package javaops.votingsystem.web.menu;

import javaops.votingsystem.model.Menu;
import javaops.votingsystem.repository.MenuRepository;
import javaops.votingsystem.repository.MenuTestData;
import javaops.votingsystem.util.JsonUtil;
import javaops.votingsystem.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static javaops.votingsystem.repository.DishTestData.*;
import static javaops.votingsystem.repository.MenuTestData.*;
import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT1_ID;
import static javaops.votingsystem.repository.UserTestData.ADMIN;
import static javaops.votingsystem.web.TestUtil.readFromJson;
import static javaops.votingsystem.web.TestUtil.userHttpBasic;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/admin/restaurants/100000/menus/";
    @Autowired
    private MenuRepository menuRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU1));
    }

    @Test
    void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU1_ID + "/with")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU1));
        DISH_MATCHER.assertMatch(menuRepository.get(MENU1_ID, RESTAURANT1_ID).getDishes(), DISHES_MENU1);
    }

    @Test
    void getWithDishesForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU1));
        DISH_MATCHER.assertMatch(menuRepository.getWithDishesForToday(RESTAURANT1_ID).getDishes(), DISHES_MENU1);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENUS_RESTAURANT1));
    }

    @Test
    void getAllWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "dishes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENUS_RESTAURANT1));
        DISH_MATCHER.assertMatch(menuRepository.getAllWithDishes(RESTAURANT1_ID).get(0).getDishes(), DISHES_MENU1);
        DISH_MATCHER.assertMatch(menuRepository.getAllWithDishes(RESTAURANT1_ID).get(1).getDishes(), DISHES_MENU2);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertNull(menuRepository.get(MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU5.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    void update() throws Exception {
        Menu updated = MenuTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MENU1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        MENU_MATCHER.assertMatch(menuRepository.get(MENU1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Menu newMenu = MenuTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu))
                .with(userHttpBasic(ADMIN)));
        Menu created = readFromJson(action, Menu.class);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuRepository.get(newId, RESTAURANT1_ID), newMenu);
    }

    @Test
    void createInvalid() throws Exception {
        Menu invalid = new Menu(null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    void updateInvalid() throws Exception {
        Menu invalid = new Menu(MENU1_ID, null, "Invalid");
        perform(MockMvcRequestBuilders.put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}