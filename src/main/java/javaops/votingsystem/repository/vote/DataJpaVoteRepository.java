package javaops.votingsystem.repository.vote;

import javaops.votingsystem.model.Vote;
import javaops.votingsystem.repository.VoteRepository;
import javaops.votingsystem.repository.restaurant.CrudRestaurantRepository;
import javaops.votingsystem.repository.user.CrudUserRepository;
import javaops.votingsystem.util.exception.IllegalRequestDataException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew()) {
            Vote voteDb = get(vote.getId(), userId);
            if (voteDb == null) {
                return null;
            } else if (voteDb.getDate().isEqual(vote.getDate()) && LocalTime.now().isBefore(LocalTime.of(11, 00))) {
                vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
            } else {
                throw new IllegalRequestDataException("Its too late, vote can't be changed!");
            }
        }
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }
}
