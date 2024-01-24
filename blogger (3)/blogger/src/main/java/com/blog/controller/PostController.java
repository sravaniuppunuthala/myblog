package com.blog.controller;

import com.blog.payload.PostDto;


import com.blog.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private  PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
//@PreAuthorize("hasRole('ADMIN')")//only  admin can create post
    @PostMapping//insert
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) {//if a method has multiple return values use ? or object,the object is supermost  class it can hold any value
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);//
            //this line will get the message title should not more than 2 characters in postdto
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
   //@ PreAuthorize("hasRole('ADMIN')")//only admin can delete the post after using preauthorize

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePost(id);//controller calls the service layer
        return new ResponseEntity<>("post is deleted sravani !!", HttpStatus.OK);
        //get all posts code
    }
    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
   // @PreAuthorize("hasRole('ADMIN')")//only admin get the post preauthorize
@GetMapping
public ResponseEntity<List<PostDto>> getAllPosts(//pageno,size give througgh the url to getAllContent in serviceiml
        @RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
        @RequestParam(name="pageSize",defaultValue = "3",required=false)int pageSize,
        @RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
        @RequestParam(name="sortDir",defaultValue="asc",required=false)String sortDir

){

    List<PostDto> postDtos=postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    return new ResponseEntity<>(postDtos,HttpStatus.OK);

    }
    //http://localhost:8080/api/posts?postId=1
    @PutMapping
    public ResponseEntity<PostDto> updatePost(
            @RequestParam("postId") long postId,
          @RequestBody  PostDto postDto
    ) {
      PostDto  dto=postService.updatePost(postId, postDto);
      return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}
















