package launchcode.org.blogliftoff.controllers;

import javafx.geometry.Pos;
import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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

    @GetMapping(value = "detail/{id}")
    public String displayPostDetails(@PathVariable int id, Model model) {
        model.addAttribute("title", "Blog Details");
        Optional<Post> post = postRepository.findById(id);
        Post post1 = post.get();
        model.addAttribute(post1);
        return "posts/details";

    }

    @GetMapping(value = "edit/{postId}")
    public String displayEditPostForm(Model model, @PathVariable int postId) {

        model.addAttribute("title", "Edit Post");

        Optional<Post> post = postRepository.findById(postId);
        model.addAttribute(post.get());
        return "posts/edit";

    }

    @PostMapping(value = "edit")
    public String processEditPostForm (@Valid @ModelAttribute Post post, Errors errors) {
        if (errors.hasErrors())
            return "posts/edit";

        postRepository.save(post);
        return "redirect:/posts/detail/" + post.getId();
    }

}
