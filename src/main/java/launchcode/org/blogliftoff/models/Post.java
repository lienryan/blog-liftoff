package launchcode.org.blogliftoff.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String title;

    @NotNull
    private String body;

    @NotNull
    private Date date;

    public Post() {
    }

    public Post(@NotNull String title, @NotNull String body, @NotNull Date date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


