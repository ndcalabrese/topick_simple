package com.ndcalabrese.topick_simple.service;

import com.ndcalabrese.topick_simple.dto.VoteDto;
import com.ndcalabrese.topick_simple.exception.PostNotFoundException;
import com.ndcalabrese.topick_simple.exception.TopickException;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Vote;
import com.ndcalabrese.topick_simple.repository.PostRepository;
import com.ndcalabrese.topick_simple.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ndcalabrese.topick_simple.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final UserServiceImpl userService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, userService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new TopickException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(userService.getCurrentUser())
                .build();
    }

}
