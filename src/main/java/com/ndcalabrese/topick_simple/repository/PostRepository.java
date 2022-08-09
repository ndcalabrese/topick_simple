package com.ndcalabrese.topick_simple.repository;

import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubtopick(Subtopick subtopick);

    List<Post> findAllByUserOrderByCreatedDateDesc(User user);

    List<Post> findAllBySubtopickOrderByVoteCountDesc(Subtopick subtopick);

    List<Post> findAllByUser(User user);

}
