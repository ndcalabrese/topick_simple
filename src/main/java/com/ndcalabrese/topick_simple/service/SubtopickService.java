package com.ndcalabrese.topick_simple.service;

import com.ndcalabrese.topick_simple.dto.SubtopickDto;
import com.ndcalabrese.topick_simple.exception.PostNotFoundException;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.repository.SubtopickRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SubtopickService {

    private final SubtopickRepository subtopickRepository;
    private final UserServiceImpl userService;

    @Transactional
    public SubtopickDto save(SubtopickDto subtopickDto) {
        Subtopick subtopick = new Subtopick(subtopickDto.getName(), subtopickDto.getDescription(), userService.getCurrentUser());
        Subtopick saved = subtopickRepository.save(subtopick);
        subtopickDto.setId(saved.getId());

        return subtopickDto;
    }

    @Transactional(readOnly = true)
    public Subtopick getSubtopickById(Long id) {
        Optional<Subtopick> optional = subtopickRepository.findById(id);

        Subtopick subtopick = null;

        if (optional.isPresent()) {
            subtopick = optional.get();
        } else {
            throw new PostNotFoundException("Post not found with ID: " + id);
        }

        return subtopick;
    }

    @Transactional(readOnly = true)
    public Subtopick getSubtopickByName(String name) {
        Optional<Subtopick> optional = subtopickRepository.findByName(name);

        Subtopick subtopick = null;

        if (optional.isPresent()) {
            subtopick = optional.get();
        } else {
            throw new PostNotFoundException("Post not found with ID: " + name);
        }

        return subtopick;
    }

    @Transactional(readOnly = true)
    public List<Subtopick> getAllSubtopicks() {

        return subtopickRepository.findAll();
    }

    public void deleteSubtopickById(long id) {
        this.subtopickRepository.deleteById(id);
    }
}

