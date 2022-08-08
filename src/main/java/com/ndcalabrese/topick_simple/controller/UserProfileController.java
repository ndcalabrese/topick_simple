package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.User;
import com.ndcalabrese.topick_simple.model.Vote;
import com.ndcalabrese.topick_simple.repository.UserRepository;
import com.ndcalabrese.topick_simple.service.CommentService;
import com.ndcalabrese.topick_simple.service.PostService;
import com.ndcalabrese.topick_simple.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserProfileController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final UserServiceImpl userService;

    @GetMapping("/profile/{username}")
    public String getAllPostsAndCommentsForUser(@PathVariable String username, Model model) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username)
                );
        List<Post> posts = postService.getAllPostsByUser(username);
        List<Comment> comments = commentService.getAllCommentsByUser(username);
        //New
        Vote vote = new Vote();
        model.addAttribute("vote", vote);

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("comments", comments);

        return "user_profile";
    }

    @GetMapping("/profile/me")
    public String getAllPostsAndCommentsForCurrentUser(Model model) {

        User user = userService.getCurrentUser();
        List<Post> posts = postService.getAllPostsByUser(user.getUsername());
        List<Comment> comments = commentService.getAllCommentsByUser(user.getUsername());

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("comments", comments);

        return "user_profile";
    }

}
