package launchcode.org.blogliftoff.repositories;

import launchcode.org.blogliftoff.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository <Role, String> {
}
