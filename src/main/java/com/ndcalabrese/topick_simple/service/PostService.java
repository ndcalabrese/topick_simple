package com.ndcalabrese.topick_simple.service;

import com.ndcalabrese.topick_simple.exception.PostNotFoundException;
import com.ndcalabrese.topick_simple.exception.SubtopickNotFoundException;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.model.User;
import com.ndcalabrese.topick_simple.repository.PostRepository;
import com.ndcalabrese.topick_simple.repository.SubtopickRepository;
import com.ndcalabrese.topick_simple.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubtopickRepository subtopickRepository;
    private final UserRepository userRepository;

    public void save(Post post) {
        this.postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        Optional<Post> optional = postRepository.findById(id);

        Post post = null;

        if (optional.isPresent()) {
            post = optional.get();
        } else {
            throw new PostNotFoundException("Post not found with ID: " + id);
        }

        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPostsBySubtopick(Long subtopickId) {
        Subtopick subtopick = subtopickRepository.findById(subtopickId)
                .orElseThrow(
                        () -> new SubtopickNotFoundException(subtopickId.toString())
                );

        return postRepository.findAllBySubtopick(subtopick);
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPostsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username)
                );

        return postRepository.findAllByUser(user);
    }

    public void deletePostById(long id) {
        this.postRepository.deleteById(id);
    }


}
