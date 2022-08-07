package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.dto.SubtopickDto;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.repository.SubtopickRepository;
import com.ndcalabrese.topick_simple.service.SubtopickService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/subtopicks")
@AllArgsConstructor
public class SubtopickController {

    private final SubtopickService subtopickService;
    private final SubtopickRepository subtopickRepository;

    @PostMapping("/create")
    public String createSubtopick(@ModelAttribute("subtopick") SubtopickDto subtopickDto) {
        subtopickService.save(subtopickDto);

        return "redirect:/api/posts/by-subtopick{" + subtopickDto.getId() + "}/?SubtopickCreationSuccess";
    }

    @GetMapping("/create")
    public String showCreateSubtopickForm(Model model) {

        Subtopick subtopick = new Subtopick();

        model.addAttribute("subtopick", subtopick);

        return "create_subtopick";
    }

    @GetMapping("/list")
    public String listAllSubtopicks(Model model) {
        List<Subtopick> subtopicks = subtopickService.getAllSubtopicks();
        model.addAttribute("subtopicks", subtopicks);

        return "all_subtopicks";
    }

//    @GetMapping("/{name}")
//    public String getSubtopick(@PathVariable Long name, Model model) {
//
//        Subtopick subtopick = subtopickService.getSubtopickByName(name);
//
//        model.addAttribute("subtopick", subtopick);
//
//        return "subtopick";
//    }


}
