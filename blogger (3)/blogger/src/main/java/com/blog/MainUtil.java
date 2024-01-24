package com.blog;

import com.blog.payload.PostDto;
import com.blog.util.Movie;
import org.springframework.web.servlet.ModelAndView;
//import com.blog.util.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainUtil {
    public static void main(String[] args) {
        //MainUtil main=new MainUtil();
        // Predicate<String> condition=y->y.equals("mike");//predicate it is a functional interface it works based on condition and it gives true or false as output like it compares values

        //boolean val=condition.test("mike");//here we assign y value using tesst() method
        //System.out.println(val);
        // List<Integer> data= Arrays.asList(10,20,30,5,50,100);
        // List<Integer> newData=data.stream().filter(x->x>20).collect(Collectors.toList());
        //System.out.println(newData);
        //Post post1=new Post();  //here we create 3 objects this is called static method mapToDto
        // post1.setId(1);
        // post1.setTitle("aaa");
        //post1.setContent("aaaa");
        // Post post2=new Post();
        //  post1.setId(2);
        // post1.setTitle("bbb");
        // post1.setContent("bbbb");
        // Post post3=new Post();
        //post1.setId(3);
        //post1.setTitle("ccc");
        //post1.setContent("cccc");

        // PostDto dto1=mapToDto(post1); if we have one lakh of code to copy its difficult we have option to use
        //or loop but stream api/collection is best chaoice
        // PostDto dto2=mapToDto(post2);
        // PostDto dto3=mapToDto(post3);
        // List<Post> posts=Arrays.asList(post1,post2,post3);//in this posts 3 objects addresses
        // List<PostDto> dtos=posts.stream().map(main::mapToDto).collect(Collectors.toList());//(p->mapToDto(p) method called using stream api is (::)using this double colon
        //calling non static method and we called static method  (MainUtil::mapToDto) without using lambdas expression.
        // List<PostDto> dtos=posts.stream().map(MainUtil::mapToDto).collect(Collectors.toList()); static method call using classname
        // List<PostDto> dtos=posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());
        // System.out.println(dtos);
        //}
        // static PostDto mapToDto(Post post){ //here we create object dto in that dto we set id,title,content(here
        // Post post data is stored first one is post1 ,post2,post3,non static method
        // PostDto dto=new PostDto();
        // dto.setId(post.getId());
        // dto.setTitle(post.getTitle());
        // dto.setContent(post.getContent());
        // return dto;
        // }

//}Maximum minimum number using stream api
        // List<Integer> numbers=Arrays.asList(10,100,200,5900,1000,5000,10000);
        Movie m1 = new Movie("bbb",8,1999);
        Movie m2 = new Movie("aaa",6,1989);
        Movie m3 = new Movie("cccc",9,2000);
        ArrayList<Movie> list=new ArrayList<Movie>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        //MovieYear movieYear=new MovieYear();
       // Collections.sort(list,movieYear);
        //MovieRating  movieRating=new MovieRating();
        //Collections.sort(list,movieRating);
        MovieName  movieName=new MovieName();
        Collections.sort(list,movieName);

        for (Movie m:list){
            System.out.println(m.getName());
            System.out.println(m.getYear());

            System.out.println(m.getRating());

        }
                    }
}
