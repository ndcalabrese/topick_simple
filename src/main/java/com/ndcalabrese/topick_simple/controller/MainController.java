package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping("/")
    public String getAllPosts(Model model) {

        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "all_posts";
    }

}
