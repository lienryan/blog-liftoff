package launchcode.org.blogliftoff.repositories;

import launchcode.org.blogliftoff.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, String> {
}
