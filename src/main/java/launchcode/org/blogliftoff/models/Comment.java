package launchcode.org.blogliftoff.models;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Comment {


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2)
    private String name;

    @NotNull
    @Size(min=2)
    private String text;
/*
    @CreationTimestamp
    private Date createDate;
*/

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;


    @NotNull
    public Comment() {
    }

    public String getName() {
        return name;
    }

    public Comment(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
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
