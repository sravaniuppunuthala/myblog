package com.blog.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String title;
    private String Description;
    private String content;
    // One-to-many mapping with comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval=true)//by using orphan we are doing composition post deleted automatically comments also deleted from memory point of view
    private List<Comment> comments = new ArrayList<>();
//OR above one and below one
   // @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)//if we remove post comments automatically removed using (orphanRemoval=true),if we make change in parent class that changes are appllied automatically to child class using(cascadeTypeAll)
   // private List<Comment> comments=new ArrayList<>();


    public  long getId() {
        return id;
    }

    public  void setId(long id) {
        this.id = id;
    }

    public  String getTitle() {
        return title;
    }

    public  void setTitle(String title) {
        this.title = title;
    }

    public  String getDescription() {
        return Description;
    }

    public  void setDescription(String description) {
        Description = description;
    }

    public  String getContent() {
        return content;
    }



    public   void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
