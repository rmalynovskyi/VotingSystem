package javaops.votingsystem.web;

import javaops.votingsystem.model.Vote;
import javaops.votingsystem.repository.VoteRepository;
import javaops.votingsystem.repository.VoteTestData;
import javaops.votingsystem.service.VoteService;
import javaops.votingsystem.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.*;

import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT1;
import static javaops.votingsystem.repository.UserTestData.*;
import static javaops.votingsystem.repository.VoteTestData.*;
import static javaops.votingsystem.web.TestUtil.readFromJson;
import static javaops.votingsystem.web.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileVoteController.REST_URL + "/";
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteService voteService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE1));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTES_OF_USER));
    }

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=100000")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isCreated());
        Vote created = readFromJson(action, Vote.class);
        created.setRestaurant(RESTAURANT1);
        int newId = created.getId();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.get(newId, USER_ID), newVote);
    }

    @Test
    void update() throws Exception {
        voteService.setClock(Clock.fixed(Instant.parse("2020-06-21T10:00:00Z"), ZoneOffset.UTC));
        Vote updated = new Vote();
        updated.setDate(LocalDate.of(2020, 6, 21));
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE1_ID + "?restaurantId=100001").contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        VOTE_MATCHER.assertMatch(voteRepository.get(VOTE1_ID, USER_ID), VOTE1_UPDATED);
    }

    @Test
    void updateTooLate() throws Exception {
        voteService.setClock(Clock.fixed(Instant.parse("2020-06-21T12:00:00Z"), ZoneOffset.UTC));
        Vote updated = new Vote();
        updated.setDate(LocalDate.of(2020, 6, 21));
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE1_ID + "?restaurantId=100001").contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}