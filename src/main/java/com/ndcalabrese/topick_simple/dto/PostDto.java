package com.ndcalabrese.topick_simple.dto;

import com.ndcalabrese.topick_simple.model.Subtopick;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String postTitle;
    private String postBody;
    private Long subtopickId;
    private String url;
}
