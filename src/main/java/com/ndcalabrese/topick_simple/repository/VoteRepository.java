package com.ndcalabrese.topick_simple.repository;


import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.User;
import com.ndcalabrese.topick_simple.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
