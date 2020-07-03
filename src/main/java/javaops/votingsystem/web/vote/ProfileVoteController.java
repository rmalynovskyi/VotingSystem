package javaops.votingsystem.web.vote;

import javaops.votingsystem.AuthorizedUser;
import javaops.votingsystem.model.Vote;
import javaops.votingsystem.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static javaops.votingsystem.util.ValidationUtil.*;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController {

    static final String REST_URL = "rest/profile/votes";
    private final VoteRepository voteRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public ProfileVoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("get vote {} for user {}", id, authorizedUser.getId());
        return checkNotFoundWithId(voteRepository.get(id, authorizedUser.getId()), id);
    }

    @GetMapping
    public List<Vote> getAll(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("get all votes for user {}", authorizedUser.getId());
        return voteRepository.getAll(authorizedUser.getId());
    }

    @PutMapping(value = "/restaurants/{restaurantId}/vote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Vote vote, @PathVariable int restaurantId, @PathVariable int id,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        assureIdConsistent(vote, id);
        log.info("update vote {} for restaurant {} by user {}", id, restaurantId, authorizedUser);
        checkNotFoundWithId(voteRepository.save(vote, authorizedUser.getId(), restaurantId), id);
    }

    @PostMapping(value = "/restaurants/{restaurantId}/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote, @PathVariable int restaurantId,
                                                   @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        checkNew(vote);
        log.info("create new vote {} for restaurant {} by user {}", vote, restaurantId, authorizedUser);
        Vote created = voteRepository.save(vote, authorizedUser.getId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
