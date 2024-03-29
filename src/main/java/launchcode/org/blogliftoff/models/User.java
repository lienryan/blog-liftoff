package launchcode.org.blogliftoff.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @Email(message = "Please provide an email")
    @NotEmpty(message = "Email may not be empty")
    @Column(unique = true)
    private String email;


    @Size(min=3, message = "Your username must have at least 3 characters")
    @NotEmpty(message = "Please provide your username")
    private String username;

    @NotEmpty (message = "Password may not be empty")
    @Size(min=4, message = "Your password must have at least 3 characters")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email")
    }, inverseJoinColumns =  { @JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })
    private List<Role> roles;


    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public boolean isPresent() {
        return true;
    }
}
