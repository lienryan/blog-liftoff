package launchcode.org.blogliftoff.repositories;


import launchcode.org.blogliftoff.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {

}
