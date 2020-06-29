package javaops.votingsystem;

import javaops.votingsystem.model.User;
import javaops.votingsystem.to.UserTo;
import javaops.votingsystem.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), "{noop}" + user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public int getId() {
        return userTo.id();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUser() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
