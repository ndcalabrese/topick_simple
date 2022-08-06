package com.ndcalabrese.topick_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Inheritance
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subtopick subtopick;

    private String postTitle;

    @Lob
    @Nullable
    private String postBody;

    private Instant createdDate = Instant.now();

    @Nullable
    private String url;

    private Integer voteCount = 1;

}
