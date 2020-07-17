package javaops.votingsystem.web;

import javaops.votingsystem.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static javaops.votingsystem.repository.MenuTestData.MENU1;
import static javaops.votingsystem.repository.MenuTestData.MENU_MATCHER;
import static javaops.votingsystem.repository.RestaurantTestData.*;
import static javaops.votingsystem.repository.UserTestData.USER;
import static javaops.votingsystem.web.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileRestaurantController.REST_URL + "/";
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getWithDayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID + "/menutoday")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1));
        MENU_MATCHER.assertMatch(restaurantRepository.getWithDayMenu(RESTAURANT1_ID).getMenus(), MENU1);
    }

    @Test
    void getAllWithMenuOfDay() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "menutoday")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_WITH_MENU_FOR_TODAY));
        MENU_MATCHER.assertMatch(restaurantRepository.getAllWithDayMenu().get(0).getMenus(), MENU1);
    }
}