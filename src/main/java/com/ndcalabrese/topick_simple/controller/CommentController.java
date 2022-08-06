package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.dto.CommentDto;
import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.repository.CommentRepository;
import com.ndcalabrese.topick_simple.repository.PostRepository;
import com.ndcalabrese.topick_simple.service.CommentService;
import com.ndcalabrese.topick_simple.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts/view-post/{id}/comments/create")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @PostMapping
    public String createComment(@PathVariable Long id, @ModelAttribute("comment") CommentDto commentDto) {
        commentDto.setPostId(id);
        commentService.save(commentDto);

        return "redirect:/api/posts/view-post/{id}/comments";
    }

    @GetMapping
    public String showCreatePostForm(@PathVariable Long id, Model model) {
        Comment comment = new Comment();
        Post post = postService.getPostById(id);

        model.addAttribute("post", post);
        model.addAttribute("comment", comment);

        return "create_comment";
    }

}

