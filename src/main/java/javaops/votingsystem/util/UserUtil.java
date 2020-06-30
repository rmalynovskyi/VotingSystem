package javaops.votingsystem.util;

import javaops.votingsystem.model.Role;
import javaops.votingsystem.model.User;
import javaops.votingsystem.to.UserTo;

public class UserUtil {

    private UserUtil() {
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
