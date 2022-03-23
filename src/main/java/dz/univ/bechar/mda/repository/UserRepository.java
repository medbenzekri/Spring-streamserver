package dz.univ.bechar.mda.repository;

import dz.univ.bechar.mda.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    //List<User> findByUserame(String name);
    User findUserByUsernameAndPassword(String name, String password);

}
