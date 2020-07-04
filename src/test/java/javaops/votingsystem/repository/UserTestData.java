package javaops.votingsystem.repository;

import javaops.votingsystem.model.Role;
import javaops.votingsystem.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javaops.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = START_SEQ + 21;
    public static final int ADMIN_ID = START_SEQ + 22;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final List<User> USERS = Arrays.asList(ADMIN, USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static User getNotExisting() {
        return new User(USER_ID + 10, "Not exist", "notExist@gmail.com", "pass", Role.USER);
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "password", "registered", "votes");
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password", "registered", "votes").isEqualTo(expected);
    }
}

