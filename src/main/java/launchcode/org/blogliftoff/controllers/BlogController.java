package launchcode.org.blogliftoff.controllers;

import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/posts")
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "")
    public String listPosts(Model model) {
        List<Post> allPosts = postRepository.findAll();
        model.addAttribute("posts", allPosts);
        return "posts/list";
    }

    @GetMapping(value = "create")
    public String displayCreatePostForm(Model model) {
        model.addAttribute(new Post());
        model.addAttribute("title", "Create Blog");
        return "posts/create";
    }

    @PostMapping(value = "create")
    public String processCreatePostForm(@Valid @ModelAttribute Post post, Errors errors) {
        if (errors.hasErrors())
            return "posts/create";

        postRepository.save(post);
        return "redirect:";

    }

    @GetMapping(value = "{id}")

    public String displayPostDetails(@PathVariable int id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        model.addAttribute("title", "Blog Details");
        model.addAttribute(post);
        return "posts/details";

    }

}
