package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.service.CommentService;
import com.ndcalabrese.topick_simple.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserProfileController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/{username}")
    public String getAllPostsAndCommentsForUser(@PathVariable String username, Model model) {

        List<Post> posts = postService.getAllPostsByUser(username);
        List<Comment> comments = commentService.getAllCommentsByUser(username);

        model.addAttribute("posts", posts);
        model.addAttribute("comments", comments);

        return "user";
    }

}
