package javaops.votingsystem.repository.user;

import javaops.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudUserRepository extends JpaRepository<User, Integer> {

}
