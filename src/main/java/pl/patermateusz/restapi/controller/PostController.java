package pl.patermateusz.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.patermateusz.restapi.model.Post;
import pl.patermateusz.restapi.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPosts(@PathVariable long id){
        return postService.getSinglePost(id);
    }
}
