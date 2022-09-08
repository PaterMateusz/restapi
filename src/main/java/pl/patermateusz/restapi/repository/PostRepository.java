package pl.patermateusz.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.patermateusz.restapi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
