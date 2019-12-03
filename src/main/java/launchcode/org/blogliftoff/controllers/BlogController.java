package launchcode.org.blogliftoff.controllers;

import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.models.User;
import launchcode.org.blogliftoff.repositories.CommentRepository;
import launchcode.org.blogliftoff.repositories.PostRepository;
import launchcode.org.blogliftoff.services.PostService;
import launchcode.org.blogliftoff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Controller
@RequestMapping(value = "/posts")
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "")
    public String listPosts(Model model) {
        List<Post> allPosts = postRepository.findAll();
        model.addAttribute("posts", allPosts);
        return "posts/list";
    }

    @GetMapping(value = "create")
    public String displayCreatePostForm(Model model,Principal principal) {

        User user = userService.findByEmail(principal.getName());
        if (user.isPresent()) {
            Post post = new Post();
            post.setUser(user);
            model.addAttribute("post", post);
            model.addAttribute("title", "Create Blog");
            return "posts/create";
        } else {
            return "Error";
        }

    }

    @PostMapping(value = "create")
    public String processCreatePostForm(@Valid @ModelAttribute Post post, Errors errors) {
        if (errors.hasErrors())
            return "posts/create";

        postRepository.save(post);
        return "redirect:/user/profile";
    }

    @GetMapping(value = "detail/{id}")
    public String displayPostDetails(@PathVariable int id, Model model) {
        model.addAttribute("title", "Blog Details");
        Optional<Post> post = postRepository.findById(id);
        Post post1 = post.get();
        model.addAttribute(post1);
        return "posts/details";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable int id,  Principal principal,  Model model) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwner(principal, post)) {
                model.addAttribute("post", post);

                return "posts/create";

            } else {

                return "Error";

            }
        }else{
            return "Error";
        }
    }


    @PostMapping(value = "delete/{id}")
    public String processDeletePost(@PathVariable int id, Model model, Principal principal) {
        Optional<Post> optionalPost = postRepository.findById(id);

        Post post = optionalPost.get();

            if (isPrincipalOwner(principal, post)) {
                postRepository.delete(post);
                return "redirect:/user/profile";
            }
            return "messageError";

    }

    private boolean isPrincipalOwner(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getEmail());
    }
}