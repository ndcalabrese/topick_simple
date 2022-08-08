package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.dto.VoteDto;
import com.ndcalabrese.topick_simple.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{postId}/upvote")
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        voteService.vote(voteDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
