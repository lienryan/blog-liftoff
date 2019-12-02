package launchcode.org.blogliftoff.controllers;


import javafx.geometry.Pos;
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
    public String displayCreatePostForm(Model model, String email,HttpSession session) {

        session.setAttribute("email", email);
        model.addAttribute(new Post());
        model.addAttribute("title", "Create Blog");

        return "posts/create";
    }

    @PostMapping(value = "create")
    public String processCreatePostForm(@Valid @ModelAttribute Post post, Errors errors, HttpSession session) {
        if (errors.hasErrors())
            return "posts/create";

        //User can create a blog
        String email = (String) session.getAttribute("email");
        postService.createPost(post, userService.findByEmail(email));

        //postRepository.save(post);
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


    @GetMapping(value = "edit/{id}")
    public String displayEditPostForm(Model model, @PathVariable int id) {
        model.addAttribute("title", "Edit Post");

        Optional<Post> post = postRepository.findById(id);

        model.addAttribute(post.get());

        return "posts/edit";

    }

    @PostMapping(value = "edit")
    public String processEditPostForm (@Valid @ModelAttribute Post post, Errors errors) {

        if (errors.hasErrors())

            return "posts/edit";

        postRepository.save(post);

        return "redirect:";

        //return "redirect:/posts/detail/" + post.getId();

    }

    @PostMapping(value = "delete/{id}")
    public String processDeletePost(@PathVariable int id, Model model, Principal principal) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipleOwner(principal, post)) {
                postRepository.delete(post);
                return "redirect:/user/profile";
            }
            return "messageError";
        }
        return "Error";
    }

    private boolean isPrincipleOwner(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getEmail());
    }
}