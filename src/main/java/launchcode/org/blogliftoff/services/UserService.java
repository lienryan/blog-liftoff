package launchcode.org.blogliftoff.services;

import launchcode.org.blogliftoff.models.Role;
import launchcode.org.blogliftoff.models.User;
import launchcode.org.blogliftoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser (User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }

    public void createAdmin (User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public boolean isUserPresent(String email) {
        User user=userRepository.findByEmail(email);
        if (user!=null)
            return true;
        return false;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
