package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.model.User;
import com.ndcalabrese.topick_simple.repository.SubtopickRepository;
import com.ndcalabrese.topick_simple.service.CommentService;
import com.ndcalabrese.topick_simple.service.PostService;
import com.ndcalabrese.topick_simple.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    private final SubtopickRepository subtopickRepository;

    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postService.save(post);

        return "redirect:/api/sub/{" + post.getSubtopick() + "}/view-post/{" + post.getPostId() + "}/comments";
    }

    @GetMapping("/api/posts/create")
    public String showCreatePostForm(Model model) {
        Post post = new Post();
        List<Subtopick> subtopicks = subtopickRepository.findAll();

        model.addAttribute("post", post);
        model.addAttribute("subtopicks", subtopicks);

        return "create_post";
    }


    @GetMapping("/")
    public String getAllPosts(Model model) {

        List<Post> posts = postService.getAllPosts();
        

        model.addAttribute("posts", posts);

        return "all_posts";
    }

    @GetMapping("/api/posts/by-subtopick/{id}")
    public String getPostsBySubtopick(@PathVariable Long id, Model model) {

        List<Post> posts = postService.getAllPostsBySubtopick(id);

        model.addAttribute("posts", posts);

        return "all_posts";
    }

    @GetMapping("/api/sub/{subtopickName}/view-post/{id}/comments")
    public String getPostWithComments(@PathVariable Long id, Model model){

        Post post = postService.getPostById(id);
        List<Comment> comments = commentService.getAllCommentsForPost(id);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "post";
    }

}
