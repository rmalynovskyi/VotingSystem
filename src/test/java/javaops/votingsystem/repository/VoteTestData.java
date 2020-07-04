package javaops.votingsystem.repository;

import javaops.votingsystem.model.Vote;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 23;
    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2020, 6, 21));
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, LocalDate.of(2020, 6, 20));
    public static final List<Vote> VOTES_OF_USER = Arrays.asList(VOTE1, VOTE2);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static void assertMatch(Vote actual, Vote expected) {
        Assertions.assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        Assertions.assertThat(actual).usingElementComparatorIgnoringFields("user", "restaurant").isEqualTo(expected);
    }
}
