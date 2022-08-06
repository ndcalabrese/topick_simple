package com.ndcalabrese.topick_simple.service;

import com.ndcalabrese.topick_simple.dto.CommentDto;
import com.ndcalabrese.topick_simple.exception.PostNotFoundException;
import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.User;
import com.ndcalabrese.topick_simple.repository.CommentRepository;
import com.ndcalabrese.topick_simple.repository.PostRepository;
import com.ndcalabrese.topick_simple.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;

    public void save(CommentDto commentDto) {
        // Query the post repository for the specific post
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(
                        () -> new PostNotFoundException(
                                commentDto.getPostId().toString()
                        )
                );
//
        Comment comment = new Comment(commentDto.getCommentBody(), post, userService.getCurrentUser());

        this.commentRepository.save(comment);
    }



    public List<Comment> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new PostNotFoundException(postId.toString())
                );

        return commentRepository.findByPost(post);
    }

    public List<Comment> getAllCommentsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username)
                );

        return commentRepository.findAllByUser(user);
    }

}
