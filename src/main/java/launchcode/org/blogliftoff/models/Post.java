package launchcode.org.blogliftoff.models;



import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private String title;


    @NotEmpty
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",nullable = false, updatable = false )
    @CreationTimestamp
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;


    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="USER_EMAIL")
    private User user;


    public Post() {
    }

    public Post(@NotNull String title, @NotNull String body, @NotNull Date createDate) {
        this.title = title;
        this.body = body;
        this.createDate = createDate;

    }

    public Post(@NotEmpty String title, @NotEmpty String body, Date createDate, Date updateDate) {
        this.title = title;
        this.body = body;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}


