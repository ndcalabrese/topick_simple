package com.ndcalabrese.topick_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long commentId;

    @NotEmpty
    private String commentBody;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    private Integer voteCount;

    private Instant createdDate = Instant.now();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public Comment(String commentBody, Post post, User user) {
        this.commentBody = commentBody;
        this.post = post;
        this.user = user;
        this.createdDate = Instant.now();
        this.voteCount = 1;
    }

}
