package javaops.votingsystem.repository.vote;

import javaops.votingsystem.model.Vote;
import javaops.votingsystem.repository.AbstractRepositoryTest;
import javaops.votingsystem.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT1_ID;
import static javaops.votingsystem.repository.UserTestData.USER_ID;
import static javaops.votingsystem.repository.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataJpaVoteRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private VoteRepository voteRepository;

    @Test
    void save() {
        Vote newVote = getNew();
        Vote created = voteRepository.save(newVote, RESTAURANT1_ID, USER_ID);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.get(newId, USER_ID), newVote);
    }

    @Test
    void get() {
        Vote actual = voteRepository.get(VOTE1_ID, USER_ID);
        VOTE_MATCHER.assertMatch(actual, VOTE1);
    }

    @Test
    void getNotOwn() {
        assertNull(voteRepository.get(VOTE1_ID, USER_ID + 1));
    }

    @Test
    void getAll() {
        List<Vote> votes = voteRepository.getAll(USER_ID);
        VOTE_MATCHER.assertMatch(votes, VOTES_OF_USER);
    }
}