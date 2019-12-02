package launchcode.org.blogliftoff.services;

import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.models.User;
import launchcode.org.blogliftoff.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void createPost (Post post, User user) {
        post.setUser(user);
        postRepository.save(post);

    }

    public List<Post> findUserPost(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
    }
}
