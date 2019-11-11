package launchcode.org.blogliftoff.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Comment {


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2)
    private String text;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @NotNull
    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
