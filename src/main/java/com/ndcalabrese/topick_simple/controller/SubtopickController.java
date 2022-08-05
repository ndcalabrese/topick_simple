package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.service.SubtopickService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/subtopicks")
@AllArgsConstructor
public class SubtopickController {

    private final SubtopickService subtopickService;

    @PostMapping
    public String createSubtopick(@ModelAttribute Subtopick subtopick) {
        subtopickService.save(subtopick);

        return "redirect:/api/posts/by-subtopick/{" + subtopick.getId() + "}";
    }

    @GetMapping("/api/posts/create")
    public String showCreateSubtopickForm(Model model) {

        Subtopick subtopick = new Subtopick();

        model.addAttribute("subtopick", subtopick);

        return "create_subtopick";
    }


}
