package launchcode.org.blogliftoff.controllers;

import launchcode.org.blogliftoff.models.Comment;
import launchcode.org.blogliftoff.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String listComments(Model model) {

        model.addAttribute("title", "Comments");

        model.addAttribute("comments", commentRepository.findAll());

        return "comments/list";
    }

    @GetMapping(value = "create")
    public String displayCommentForm(Model model) {
        model.addAttribute(new Comment());
        model.addAttribute("title", "Create Comment");
        return "comments/create";
    }

    @PostMapping(value = "create")
    public String processCommentForm(@Valid @ModelAttribute Comment comment, Errors errors) {
        if (errors.hasErrors())
            return "comments/create";

        commentRepository.save(comment);
        return "redirect:";
    }

}
