package com.blog.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comments")
public class Comment {//this comment map to the post object
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private String email;
    private String name;
    @ManyToOne
    @JoinColumn(name = "post_id")//here we perform manyToOne it takes third so we using(@JionColum name="post_id") create the 3rd table,post_id is foreign  key we are join the table
    private Post post;//here many comments post to one post object here used (many to one)relation
//onetomany map to the post object to comments ,post object map with many commentsArrayList it can hold multiple objects

}


