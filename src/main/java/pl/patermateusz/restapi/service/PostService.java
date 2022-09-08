package pl.patermateusz.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.patermateusz.restapi.model.Post;
import pl.patermateusz.restapi.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }
}
