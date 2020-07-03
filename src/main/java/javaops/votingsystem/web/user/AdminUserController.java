package javaops.votingsystem.web.user;

import javaops.votingsystem.model.User;
import javaops.votingsystem.repository.UserRepository;
import javaops.votingsystem.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static javaops.votingsystem.util.UserUtil.*;
import static javaops.votingsystem.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {
    static final String REST_URL = "rest/admin/users";
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getAll() {
        log.info("get all users");
        return userRepository.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get user {}", id);
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody UserTo userTo) {
        checkNew(userTo);
        log.info("create user {}", userTo);
        User user = createNewFromTo(userTo);
        User created = userRepository.save(prepareToSave(user, passwordEncoder));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete user {}", id);
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, @PathVariable int id) {
        assureIdConsistent(userTo, id);
        User user = updateFromTo(get(id), userTo);
        log.info("update user {} with id {}", userTo, id);
        checkNotFound(userRepository.save(prepareToSave(user, passwordEncoder)), "id " + id);
    }

    @GetMapping("/by")
    public User getByMail(@RequestParam String email) {
        log.info("get user by email {}", email);
        return userRepository.getByEmail(email);
    }
}
