
package launchcode.org.blogliftoff.repositories;

import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
}
