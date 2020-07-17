package javaops.votingsystem.repository;

import javaops.votingsystem.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int restaurantId, int userId);

    Vote get(int id, int userId);

    List<Vote> getAll(int userId);
}
