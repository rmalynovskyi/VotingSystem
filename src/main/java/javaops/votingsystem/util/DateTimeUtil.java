package javaops.votingsystem.util;

import javaops.votingsystem.model.Vote;

import java.time.LocalTime;

public class DateTimeUtil {
    private DateTimeUtil() {
    }

    public static boolean isTimeToChangeVote(Vote vote, Vote voteDb) {
        return voteDb.getDate().isEqual(vote.getDate()) && LocalTime.now().isBefore(LocalTime.of(23, 0));
    }
}
