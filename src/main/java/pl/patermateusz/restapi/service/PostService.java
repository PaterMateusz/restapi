package pl.patermateusz.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.patermateusz.restapi.model.Post;
import pl.patermateusz.restapi.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private static final int SIZE = 20;
    private final PostRepository postRepository;

    public List<Post> getPosts(int page){
        return postRepository.findAllPosts(PageRequest.of(page, SIZE));
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }
}
