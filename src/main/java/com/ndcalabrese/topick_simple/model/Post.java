package com.ndcalabrese.topick_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Inheritance
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

    private Instant createdDate;

    @Nullable
    private String url;

    private Integer voteCount;

    // With URL
    public Post(String postTitle, String postBody, Subtopick subtopick, String url, User user) {
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.subtopick = subtopick;
        this.url = url;
        this.user = user;
        this.createdDate = Instant.now();
        this.voteCount = 1;
    }

    public String ConvertDate () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy' 'HH:mm")
                .withZone(ZoneId.systemDefault());

        return formatter.format(this.createdDate);
    }

}
