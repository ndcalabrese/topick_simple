package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.repository.SubtopickRepository;
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
    private final SubtopickRepository subtopickRepository;

    @GetMapping("/")
    public String getAllPosts(Model model) {

        List<Post> posts = postService.getAllPosts();
        List<Subtopick> subtopicks = subtopickRepository.findAll();

        model.addAttribute("subtopicks", subtopicks);
        model.addAttribute("posts", posts);

        return "all_posts";
    }

}
