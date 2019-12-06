package launchcode.org.blogliftoff.controllers;

import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping(value = "")
    public String listPosts(Model model) {
        List<Post> allPosts = postRepository.findAll();
        model.addAttribute("posts", allPosts);
        return "posts/list";
    }

}
