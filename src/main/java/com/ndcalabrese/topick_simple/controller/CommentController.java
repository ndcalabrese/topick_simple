package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String createComment(@ModelAttribute Comment comment) {
        commentService.save(comment);

        return "redirect:/api/sub/{" + comment.getPost().getSubtopick() + "}/view-post/{" + comment.getPost().getPostId() + "}/comments";
    }

}

