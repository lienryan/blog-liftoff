package launchcode.org.blogliftoff.controllers;

import javafx.geometry.Pos;
import launchcode.org.blogliftoff.models.User;
import launchcode.org.blogliftoff.repositories.UserRepository;
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

    //@Autowired
    //private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "user/list";
    }

    @GetMapping(value="register")
    public String add(Model model){
        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());
        return "user/register";
    }
    @PostMapping(value = "register")
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {
        if (errors.hasErrors()) {
            model.addAttribute(user);
            return "user/register";
        }


/*        if (user.getPassword().equals(verify)) {
            model.addAttribute("user",user);
            return "user/index";
        }
        model.addAttribute("errorMessage", "Password and verify must match. Please try again.");
*/

        if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "user/register";
        }

       userService.createUser(user);
        return "user/index";
    }

    @GetMapping("/login")
    public String login (Model model) {

        return "user/login";
    }

    @GetMapping("profile")
    public String showUserProfile(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("posts", postService.findUserPost(user));
        return "user/profile";
    }

}
