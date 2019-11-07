
package launchcode.org.blogliftoff.repositories;

import launchcode.org.blogliftoff.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {
}
