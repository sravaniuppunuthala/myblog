package com.blog.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Title should be at least 2 characters")//size is applied on string,if you want to your own
    // message write title should be at least 2chr,if we don't want it tkes default value.
    private String title;
    @NotEmpty
    @Size(min = 4,message = "Description should be at least 4 characters")
    private String description;
    @NotEmpty
    @Size(min = 4,message = "Content should be at least 4 characters")//if error occurs in controller layer it is not
    // send to service layer,controller shows on postman,validation check all the data in controller if data is correct
    // it gives to service otherwise shows error in postman
    private String content;

   // private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   // public String getMessage() {
        //return message;
    }

    //public void setMessage(String message) {
      //  this.message = message;
   // }
//}
