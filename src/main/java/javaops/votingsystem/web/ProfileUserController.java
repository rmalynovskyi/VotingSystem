package javaops.votingsystem.web;

import javaops.votingsystem.AuthorizedUser;
import javaops.votingsystem.model.User;
import javaops.votingsystem.repository.UserRepository;
import javaops.votingsystem.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static javaops.votingsystem.util.UserUtil.*;
import static javaops.votingsystem.util.ValidationUtil.*;

@RestController
@RequestMapping(ProfileUserController.REST_URL)
public class ProfileUserController {
    static final String REST_URL = "/rest/profile";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public ProfileUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get user {}", authUser);
        return checkNotFoundWithId(userRepository.get(authUser.getId()), authUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("delete user {}", authUser);
        checkNotFoundWithId(userRepository.delete(authUser.getId()), authUser.getId());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        checkNew(userTo);
        log.info("registration of new user {}", userTo);
        User user = createNewFromTo(userTo);
        User created = userRepository.save(prepareToSave(user, passwordEncoder));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        assureIdConsistent(userTo, authUser.getId());
        log.info("update user {} with {}", authUser, userTo);
        User user = updateFromTo(get(authUser), userTo);
        checkNotFoundWithId(userRepository.save(prepareToSave(user, passwordEncoder)), authUser.getId());
    }
}
