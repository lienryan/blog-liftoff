package launchcode.org.blogliftoff.controllers;


import launchcode.org.blogliftoff.models.Comment;
import launchcode.org.blogliftoff.models.Post;
import launchcode.org.blogliftoff.repositories.CommentRepository;
import launchcode.org.blogliftoff.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "comments")
public class CommentController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String listComments(Model model) {

        model.addAttribute("title", "Comments");

        model.addAttribute("comments", commentRepository.findAll());

        return "comments/list";
    }

    @GetMapping(value = "create/{id}")
    public String displayCommentForm(Model model, @PathVariable int id) {
        Optional<Post> post = postRepository.findById(id);
        Comment comment = new Comment();
        comment.setPost(post.get());

        model.addAttribute("comment", comment);
        return "comments/create";
    }

    @PostMapping(value = "create")
    public String processCommentForm(@Valid @ModelAttribute Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return "comments/create";
        }
        commentRepository.save(comment);
        return "redirect:/posts/detail/" + comment.getPost().getId();
    }

}
