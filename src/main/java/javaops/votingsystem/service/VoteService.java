package javaops.votingsystem.service;

import javaops.votingsystem.model.Vote;
import javaops.votingsystem.repository.VoteRepository;
import javaops.votingsystem.util.exception.IllegalRequestDataException;
import javaops.votingsystem.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

import static javaops.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    public static final LocalTime LAST_TIME_TO_VOTE_TODAY = LocalTime.of(11, 0);
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote, int restaurantId, int userId) {
        return voteRepository.save(vote, restaurantId, userId);
    }

    public void update(Vote vote, int restaurant, int userId) {
        Vote voteDb = get(vote.getId(), userId);
        if (!vote.isNew() && voteDb == null) {
            throw new NotFoundException("Vote not found!");
        }
        if (!isDateTimeToChangeVote(vote, voteDb)) {
            throw new IllegalRequestDataException("Its too late, vote can't be changed!");
        }
        checkNotFoundWithId(voteRepository.save(vote, restaurant, userId), vote.getId());
    }

    public Vote get(int id, int userId) {
        return voteRepository.get(id, userId);
    }

    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

    private static boolean isDateTimeToChangeVote(Vote vote, Vote voteDb) {
        return voteDb.getDate().isEqual(vote.getDate()) && LocalTime.now().isBefore(LAST_TIME_TO_VOTE_TODAY);
    }
}
