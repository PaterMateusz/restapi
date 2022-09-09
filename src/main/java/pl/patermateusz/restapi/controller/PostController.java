package pl.patermateusz.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.patermateusz.restapi.controller.dto.PostDto;
import pl.patermateusz.restapi.controller.dto.PostDtoMapper;
import pl.patermateusz.restapi.model.Post;
import pl.patermateusz.restapi.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 1 ? page : 1;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber-1, sort));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 1 ? page : 1;
        return postService.getPostsWithComments(pageNumber-1, sort);
    }



    @GetMapping("/posts/{id}")
    public Post getPosts(@PathVariable long id) {
        return postService.getSinglePost(id);
    }
}
