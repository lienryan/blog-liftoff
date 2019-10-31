package launchcode.org.blogliftoff.models.data;

import launchcode.org.blogliftoff.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
}
