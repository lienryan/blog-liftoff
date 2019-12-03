package launchcode.org.blogliftoff.repositories;

import launchcode.org.blogliftoff.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
