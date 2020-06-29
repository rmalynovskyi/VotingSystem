package javaops.votingsystem.repository;

import javaops.votingsystem.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    User getWithVotes(int id);
}
