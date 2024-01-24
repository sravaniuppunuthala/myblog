package com.blog.service.service.impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;

import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;


    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());//this entity data is converted to dto
        post.setContent(postDto.getContent());//before saving

        Post savedPost = postRepo.save(post);//save post is return savedPost
        PostDto dto = new PostDto();//converting from entity to dto data
        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());//after saving data saved data showing on postman id num,title name,description,
        dto.setContent(savedPost.getContent());//and message it gives response
        //dto.setMessage("post is created sravani");after deleting this in postman it is not showing null value

        return dto;
    }

    @Override
    public void deletePost(long id) {
        //  postRepo.deleteById(id);
        //  Optional<Post> byId= postRepo.findById(id);
        // if (byId.isPresent()){
        // postRepo.deleteById(id);
        // }else{
        //throw new ResourceNotFoundException("post not found with id:"+id);//this only throwing exception not handling
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id:" + id)//here we used lambdas expression
                // while checking resources is not there we create lambdas expression if it s not there trow an error
                //here it throw an error not handling
        );
        postRepo.deleteById(id);

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {//getAllPosts will take pageno,size and that will give pageable
        //reference and pageable reference give to findAll and findAll will return collecction of pageeposts to convert page to list used getContent()
        //getContent will convert all pagepossts into list
        Sort sort  =(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortDir).ascending():Sort.by(sortDir).descending();
        Pageable pageable=PageRequest.of(pageNo,pageSize, Sort.by(sortBy));//sortBy it gives error it is not supposed by string it is converted object sort ,now convert string to sort using this( Sort.by(sortBy))method it is automatically converted
        Page<Post> pagePosts=postRepo.findAll(pageable);
        List<Post> posts=pagePosts.getContent();
        List<PostDto> dtos=posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post not found with id:"+postId)
        );
       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setDescription(postDto.getDescription());
       Post savedPost=postRepo.save(post);
PostDto dto=mapToDto(savedPost);
        return dto;
    }

    //@Override
    //public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
      // List<Post> posts= postRepo.findAll();
     // List<PostDto> dtos=posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());//here posts data is stored in dtos
       // return dtos;// and return dtos
   // }









    PostDto  mapToDto(Post post){//this method takes an entity data and convert to dto

      PostDto dto = new PostDto();
      dto.setId(post.getId());//it will  copy all data post to dto
      dto.setTitle(post.getTitle());
      dto.setDescription(post.getDescription());
      dto.setContent(post.getContent());
      return dto;// and return dto


    }




}

