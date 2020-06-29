package javaops.votingsystem.util;

import javaops.votingsystem.model.User;
import javaops.votingsystem.to.UserTo;

public class UserUtil {

    private UserUtil() {
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
