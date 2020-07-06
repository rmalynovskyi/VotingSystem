package javaops.votingsystem.repository.user;

import javaops.votingsystem.model.User;
import javaops.votingsystem.repository.AbstractRepositoryTest;
import javaops.votingsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static javaops.votingsystem.repository.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class DataJpaUserRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void create() {
        User newUser = getNew();
        User created = userRepository.save(new User(newUser));
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.get(newId), newUser);
    }

    @Test
    void update() {
        User updated = getUpdated();
        userRepository.save(new User(updated));
        USER_MATCHER.assertMatch(userRepository.get(USER_ID), updated);
    }

    @Test
    void updateNotFound() {
        User notExisting = getNotExisting();
        assertNull(userRepository.save(notExisting));
    }

    @Test
    void delete() {
        userRepository.delete(USER_ID);
        assertNull(userRepository.get(USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertFalse(userRepository.delete(USER_ID + 10));
    }

    @Test
    void get() {
        User user = userRepository.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void getNotFound() {
        assertNull(userRepository.get(1));
    }

    @Test
    void getByEmail() {
        User user = userRepository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void getAll() {
        List<User> all = userRepository.getAll();
        USER_MATCHER.assertMatch(all, USERS);
    }
}