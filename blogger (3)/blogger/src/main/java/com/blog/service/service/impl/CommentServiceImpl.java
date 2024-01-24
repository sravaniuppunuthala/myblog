package com.blog.service.service.impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.payload.PostDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;


    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post is not found with id:" + postId)
        );
        Comment comment=new Comment();//comment object
        comment.setName(commentDto.getName());//copy data dto to entity bcoz crud operations only done in entity
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());//this comment all dto data stored in this comment
        comment.setPost(post);//in post object set to comment

        Comment savedComment=commentRepository.save(comment);//here we saved the comment

        CommentDto dto=new CommentDto();//convert saved Comment into dto

        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());


        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
Comment comment=commentRepository.findById(commentId).orElseThrow(
        ()->new ResourceNotFoundException("comment not found with id:"+commentId)
        );
commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId( long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);//we need to convert all comments into dto
        List<CommentDto> commentDtos=comments.stream().map(c->mapToDto(c)).collect(Collectors.toList());
return commentDtos;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments=commentRepository.findAll();//find all the comments
        List<CommentDto> dtos=comments.stream().map(c->mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CommentDto updatePost(long postId, CommentDto commentDto) {

            Comment  comment=commentRepository.findById(postId).orElseThrow(
                    ()->new ResourceNotFoundException("post not found with id:"+postId)
            );
            comment.setName(commentDto.getName());
            comment.setEmail(commentDto.getEmail());
            comment.setBody(commentDto.getBody());
            Comment savedComment=commentRepository.save(comment);
            CommentDto dto=mapToDto(savedComment);
            return dto;

    }


    CommentDto  mapToDto(Comment comment){
        CommentDto dto=new CommentDto();

        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
            return dto;//this should return back dto
    }

    }
