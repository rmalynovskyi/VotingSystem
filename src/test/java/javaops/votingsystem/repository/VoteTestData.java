package javaops.votingsystem.repository;

import javaops.votingsystem.TestMatcher;
import javaops.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT1;
import static javaops.votingsystem.repository.RestaurantTestData.RESTAURANT2;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "user");
    public static final int VOTE1_ID = START_SEQ + 23;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2020, 6, 21), RESTAURANT1);
    public static final Vote VOTE1_UPDATED = new Vote(VOTE1_ID, LocalDate.of(2020, 6, 21), RESTAURANT2);
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, LocalDate.of(2020, 6, 20), RESTAURANT2);
    public static final List<Vote> VOTES_OF_USER = List.of(VOTE1, VOTE2);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), RESTAURANT1);
    }
}
