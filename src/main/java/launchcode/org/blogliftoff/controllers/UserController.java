package launchcode.org.blogliftoff.controllers;


import launchcode.org.blogliftoff.models.User;
import launchcode.org.blogliftoff.services.PostService;
import launchcode.org.blogliftoff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @GetMapping("")
    public String listUsers(Model model) {
        //TODO Make a list by searching by username
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "user/list";
    }

    @GetMapping(value="register")
    public String add(Model model){
        model.addAttribute("user", new User());
        return "user/register";
    }
    @PostMapping(value = "register")
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {
        if (errors.hasErrors()) {
            model.addAttribute(user);
            return "user/register";
        }


        if (!user.getPassword().equals(verify)) {
            model.addAttribute(user);
            model.addAttribute("errorMessage", "Password and Verify must match. Please try again.");
            return "user/register";
        }


        if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("errorMessage", "User with this email already exist");
            return "user/register";
        }

       userService.createUser(user);
        return "user/index";
    }

    @GetMapping("/login")
    public String login (Model model,Principal principal) {
        if (principal !=null){
            return "redirect:/user/profile";
        }

        return "user/login";
    }

    @GetMapping("profile")
    public String showUserProfile(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("posts", postService.findUserPost(user));
        return "user/profile";
    }
    @PostMapping(value = "delete/{email}")
    public String processDeleteUser(@PathVariable String email, Model model) {
        User user=userService.findByEmail(email);
        //TODO: Only Admin can delete user
        return "redirect:";
    }
}
