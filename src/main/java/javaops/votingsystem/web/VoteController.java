package javaops.votingsystem.web;

import javaops.votingsystem.model.Vote;
import javaops.votingsystem.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static javaops.votingsystem.util.ValidationUtil.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    static final String REST_URL = "/votes";
    private final VoteRepository voteRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote {} for user {}", id, userId);
        return checkNotFoundWithId(voteRepository.get(id, userId), id);
    }

    @GetMapping
    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        return voteRepository.getAll(userId);
    }

    @PutMapping(value = "/restaurants/{restaurantId}/vote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable int restaurantId, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, id);
        voteRepository.save(vote, userId, restaurantId);
    }

    @PostMapping(value = "/restaurants/{restaurantId}/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote, @PathVariable int restaurantId) {
        checkNew(vote);
        int userId = SecurityUtil.authUserId();
        Vote created = voteRepository.save(vote, userId, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
