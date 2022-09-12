package pl.patermateusz.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.patermateusz.restapi.model.Comment;
import pl.patermateusz.restapi.model.Post;
import pl.patermateusz.restapi.repository.CommentRepository;
import pl.patermateusz.restapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private static final int SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, SIZE, Sort.by(sort, "id")));
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> Posts = postRepository.findAllPosts(PageRequest.of(page, SIZE, Sort.by(sort, "id")));
        List<Long> ids = Posts.stream()
                .map(post -> post.getId())
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        Posts.forEach(post -> post.setCommentList(extractComments(post.getId(), comments)));
        return Posts;
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }

    private List<Comment> extractComments(long id, List<Comment> comments) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        return postEdited;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
